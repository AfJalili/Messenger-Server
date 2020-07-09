package server.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.*;
import org.bson.Document;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import static com.mongodb.client.model.Filters.*;


public class DAOImpl implements DAO {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Boolean createNewAccount(NewAccount newAccount) {
        //  check availability of accountName
        if (!isAccountNameAvailable(newAccount.getAccountName())) { return false; }
        //  create an object of Account.class and fill it with new Account info
        Account account = fillAccountObj(newAccount);
        //  add info to AccountsColl
        saveAccount(account);
        // Tcreate contact list and conversation list
        createFieldInAccountDoc( "list", "contacts", newAccount.getAccountName());
        createFieldInAccountDoc( "list", "conversationIds", newAccount.getAccountName());

        return true;
    }

    protected void createFieldInAccountDoc(String type, String fieldName, String accName) {
        BasicDBObject findQuery = new BasicDBObject("accountName", accName);
        if (type.equals("list")) {
            List<Long> list = new ArrayList<>();
            MongoDBProperty.accountsCol.findOneAndUpdate(findQuery, Updates.pushEach(fieldName, list));
        } else if (type.equalsIgnoreCase("string")) {
            MongoDBProperty.accountsCol.findOneAndUpdate(findQuery, Updates.set(fieldName, ""));
        }

    }

    protected boolean isAccountNameAvailable(String accountName) {
        LoginData ld =  MongoDBProperty.accColByLoginData.find(eq("accountName", accountName)).first();
        return ld == null;

    }

    protected Account fillAccountObj(NewAccount newAccount) {
        return new Account(newAccount.getGender(), newAccount.getAccountName(), newAccount.getUserName(),
                newAccount.getPassword(), newAccount.getProfilePic());
    }

    protected void saveAccount(Account account) {
        MongoDBProperty.accountsColByObj.insertOne(account);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Serializable messageHandler(NewMessage newMessage) {
        if (newMessage.getConversationId() == 0) {
            // TODO if not: create conversation and add it to user account info
            return createNewPvConversation(newMessage);
        }
        // TODO must change, no need for message content
        updateConversationInfo(newMessage.getConversationId(), newMessage.getDate(), newMessage.getContent());
        //save the message
        Message m = fillMessageObj(newMessage);
        saveMessage(m);

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Boolean checkLogin(LoginData loginData) {
        //  check logins in accounts
        if (!searchAccountsCollForLogin(loginData)) { return false; }
        changeUserStatusTo(loginData.getAccountName(), "ONLINE");
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<AccNameAndProfilePic> getAllUsersInfo() {
        MongoCollection<AccNameAndProfilePic> accCol = MongoDBProperty.messengerDb.getCollection("accounts", AccNameAndProfilePic.class);
        ArrayList<AccNameAndProfilePic> resultList = new ArrayList<>();
        Consumer<AccNameAndProfilePic> extractBlock = resultList::add;
        accCol.find().forEach(extractBlock);
        return resultList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<Object> getConversationInfo(String accName) {
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(getPvChatInfo(accName));
        // TODO get group info
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public UserInfo getUserInfo(String accName) {
        ArrayList<ConversationInfo> conInfos = new ArrayList<>(getConversationInfos(accName));
        // TODO get contacts
        Account ac = MongoDBProperty.accountsColByObj.find(eq("accountName", accName)).first();
        if (ac != null) {
            return new UserInfo(ac.getGender(), ac.getAccountName(), ac.getUserName(),ac.getProfilePic(), conInfos);
        }
        System.out.println("returning null in UserInfo");
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public NewMessage newMessageListener(String accName) {
        ArrayList<Long> conIds = getConversationIdList(accName);
        for (Long id : conIds) {
            BasicDBObject newDocument = new BasicDBObject(); newDocument.put("seen", true);
            BasicDBObject updateObject = new BasicDBObject(); updateObject.put("$set", newDocument);
            Message m = MongoDBProperty.messageCol.findOneAndUpdate(Filters.and(eq("conversationId", id),
                    eq("seen", false), Filters.not(eq("sender", accName))), updateObject);
            if (m != null) {
                return fillNewMessageObj(m);
            }
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public AllMessages getAllMessagesOfConversation(RequestConversation rc) {
        FindIterable<Message> messages = MongoDBProperty.messageCol.find(eq("conversationId", rc.id));
        ArrayList<Message> m = new ArrayList<>();
        for (Message message: messages) {
            m.add(message);
        }
        return new AllMessages(m);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public SearchResult searchAccName(String searchExpr) {
        FindIterable<MemberInfo> it = MongoDBProperty.accColByMemberInfo.find(Filters.regex("accountName", searchExpr));
        ArrayList<MemberInfo> members = new ArrayList<>();
        for (MemberInfo mi : it) {
            members.add(mi);
        }
        return new SearchResult(members);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Boolean addAccProfilePic(String aacName, String img) {
        createFieldInAccountDoc("String", "profilePic", aacName);
        MongoDBProperty.accountsCol.updateOne(eq("accountName", aacName), Updates.set("profilePic", img));
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Boolean changeAccProfilePic(String aacName, String img) {
        MongoDBProperty.accountsCol.updateOne(eq("accountName", aacName), Updates.set("profilePic", img));
        return true;
    }

    protected NewMessage fillNewMessageObj(Message m) {
        return new NewMessage(m.getConversationId(), m.getSender(), m.getReceiver(), m.getContent(),m.isContainsFile(), m.getDate());
    }

    protected ArrayList<ConversationInfo> getConversationInfos(String accName) {
        ArrayList<Long> conIds = new ArrayList<>(getConversationIdList(accName));
        ArrayList<ConversationInfo> result = new ArrayList<>();
        for (Long id : conIds) {
            PrivateChat pvc = MongoDBProperty.pvChatCol.find(eq("chatId", id)).first();
            if (pvc != null) {
                // creating an arraylist of members accountName
                ArrayList<String> members = new ArrayList<>();
                members.add(pvc.getMember1()); members.add(pvc.getMember2());

                result.add(new ConversationInfo(id, "pv", findLastMessage(id, pvc.getLastMessageDate()),
                       getMembersInfo(members), pvc.getLastMessageDate()));
            }
        }
        Collections.sort(result);

        return result;
    }

    protected ArrayList<MemberInfo> getMembersInfo(ArrayList<String> membersAccName) {
        ArrayList<MemberInfo> result = new ArrayList<>();
        for (String accName : membersAccName) {
            MemberInfo mi = MongoDBProperty.accColByMemberInfo.find(eq("accountName", accName)).first();
            if (mi != null) { result.add(mi); }
        }
        return result;
    }

    protected ArrayList<MemberInfo> getMembersInfo(String... membersAccName) {
        ArrayList<MemberInfo> result = new ArrayList<>();
        for (String accName : membersAccName) {
            MemberInfo mi = MongoDBProperty.accColByMemberInfo.find(eq("accountName", accName)).first();
            if (mi != null) { result.add(mi); }
        }
        return result;
    }

    protected Message findLastMessage(Long conversationId, Date lastMessageDate) {
        Message message = MongoDBProperty.messageCol.find(Filters.and(eq("conversationId", conversationId), eq("date", lastMessageDate))).first();
        if (message == null) {
            System.out.println("lastMessageNotFound");
            return null;
        }
        return message;
    }

    protected ArrayList<Long> getConversationIdList(String accName) {
        Document doc = MongoDBProperty.accountsCol.find(eq("accountName", accName)).first();
        return (ArrayList<Long>) doc.get("conversationIds");
    }

    protected ArrayList<PrivateChat> getPvChatInfo(String accName) {
        ArrayList<PrivateChat> result = new ArrayList<>();
        ArrayList<Long> conIds = getConversationIdList(accName);
        for (Long id : conIds) {
            PrivateChat pvc =  MongoDBProperty.pvChatCol.find(eq("chatId", id)).first();
            if (pvc != null) { result.add(pvc); }
        }
        return result;
    }

    // TODO must change
    protected void updateConversationInfo(long conversationId, Date lastMessageDate, String lastMessageContent) {
        BasicDBObject update =new BasicDBObject()
                .append("lastMessageDate", lastMessageDate)
                .append("lastMessageContent", lastMessageContent);
        BasicDBObject newInfo = new BasicDBObject().append("$set", update);
        MongoDBProperty.pvChatCol.updateOne(eq("chatId", conversationId), newInfo);
    }

    protected ConversationInfo createNewPvConversation(NewMessage firstMessage) {
        PrivateChat pvChatInfo = new PrivateChat(getId(), firstMessage.getSenderAccName(),
                firstMessage.getReceiverAccName(), firstMessage.getDate(), firstMessage.getContent());
        saveConversationInfo(pvChatInfo);
        firstMessage.setConversationId(pvChatInfo.getChatId());
        addConversationIdToUserAccount(pvChatInfo.getChatId(), pvChatInfo.getMember1());
        addConversationIdToUserAccount(pvChatInfo.getChatId(), pvChatInfo.getMember2());
        Message m = fillMessageObj(firstMessage);
        saveMessage(m);

        return new ConversationInfo(pvChatInfo.getChatId(), "pv", m,
                   getMembersInfo(pvChatInfo.getMember1(), pvChatInfo.getMember2()),
                   pvChatInfo.getLastMessageDate());
    }

    // TODO this implementation must change
    protected void saveConversationInfo(Object conInfo) {
        MongoDBProperty.pvChatCol.insertOne((PrivateChat) conInfo);
    }

    protected void addConversationIdToUserAccount(long conversationId, String accName) {
        MongoDBProperty.accountsCol.findOneAndUpdate(eq("accountName", accName),
                   Updates.addToSet("conversationIds", conversationId));

    }

    protected Message fillMessageObj(NewMessage nM) {
        return new Message(nM.getConversationId(), nM.getSenderAccName(),
                nM.getReceiverAccName(), nM.getContent(), nM.getDate(),false, nM.isContainsFile());
    }

    protected void saveMessage(Message message) {
        if (message.getContent() != null && !message.getContent().equals("")) {
           MongoDBProperty.messageCol.insertOne(message);
        }
    }

    protected boolean searchAccountsCollForLogin(LoginData loginData) {
        LoginData ld = MongoDBProperty.accColByLoginData.find(eq("accountName", loginData.getAccountName())).first();
        return (ld != null) && (ld.getPassword().contentEquals(loginData.getPassword()));
    }

    protected void changeUserStatusTo(String accountName, String status) { // ONLINE or OFFLINE
            Document doc = new Document(); doc.put("status", status);
            BasicDBObject update = new BasicDBObject(); update.put("$set", doc);
            MongoDBProperty.accountsCol.updateOne(eq("accountName", accountName), update);

    }

    public synchronized long getId() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Date().getTime() / 1000;
    }
}

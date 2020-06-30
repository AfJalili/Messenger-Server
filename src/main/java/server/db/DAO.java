package server.db;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import enums.UserStatus;
import model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DAO implements DbAccessObj {


    @Override
    public Boolean createNewAccount(NewAccount newAccount) {
        //  check availability of accountName
        if (!isAccountNameAvailable(newAccount.getAccountName())) { return false; }
        //  create an object of Account.class and fill it with new Account info
        Account account = fillAccountObj(newAccount);
        //  add info to AccountsColl
        saveAccount(account);
        return true;
    }

    protected boolean isAccountNameAvailable(String accountName) {
        LoginData ld =  DBStuff.accColByLoginData.find(eq("accountName", accountName)).first();
        return ld == null;

    }



    protected Account fillAccountObj(NewAccount newAccount) {
        return new Account(newAccount.getGender(), newAccount.getAccountName(), newAccount.getUserName(),
                newAccount.getPassword(), newAccount.getProfilePic());
    }

    protected void saveAccount(Account account) {
        MongoCollection<Account> accCollObj = DBStuff.messengerDb.getCollection("accounts", Account.class);
        DBStuff.accountsColByObj.insertOne(account);
    }

    @Override
    public Boolean checkLogin(LoginData loginData) {
        //  check logins in accounts
        if (!searchAccountsCollForLogin(loginData)) { return false; }
        // TODO if true: change status to online
        changeUserStatusTo(loginData.getAccountName(), UserStatus.ONLINE);
        return null;
    }

    @Override
    public Boolean MessageHandler(NewMessage newMessage) {
        if (newMessage.getConversationId() == 0) {
            // TODO if not: create conversation and add it to user account info
            return createNewConversation(newMessage);
        }
        // TODO if chat Id exists: update conversation info
        updateConversationInfo(newMessage.getConversationId(), newMessage.getDate(), newMessage.getContent());
        // TODO save the message
        Message m = fillMessageObj(newMessage);
        saveMessage(m);
        // TODO notify receiver(s)
        return null;
    }

    @Override
    public ArrayList<AccNameAndProfilePic> getAllUsersInfo() {
        MongoCollection<AccNameAndProfilePic> accCol = DBStuff.messengerDb.getCollection("accounts", AccNameAndProfilePic.class);
        ArrayList<AccNameAndProfilePic> resultList = new ArrayList<>();
        Consumer<AccNameAndProfilePic> extractBlock = resultList::add;
        accCol.find().forEach(extractBlock);
        return resultList;
    }

    // TODO must change
    protected void updateConversationInfo(long conversationId, Date lastMessageDate, String lastMessageContent) {
        BasicDBObject update =new BasicDBObject()
                .append("lastMessageDate", lastMessageDate)
                .append("lastMessageContent", lastMessageContent);
        BasicDBObject newInfo = new BasicDBObject().append("$set", update);
        DBStuff.pvChatCol.updateOne(eq("chatId", conversationId), newInfo);
    }

    protected boolean createNewConversation(NewMessage firstMessage) {
        PrivateChat pvChatInfo = new PrivateChat(getId(), firstMessage.getSenderAccName(),
                firstMessage.getReceiverAccName(), firstMessage.getDate(), firstMessage.getContent());
        saveConversationInfo(pvChatInfo);
        firstMessage.setConversationId(pvChatInfo.getChatId());
        addConversationIdToUserAccount(pvChatInfo.getChatId());
        Message m = fillMessageObj(firstMessage);
        saveMessage(m);
        // TODO notify receiver(s)
        return true;
    }
    // TODO this implementation must change
    protected void saveConversationInfo(Object conInfo) {
        DBStuff.pvChatCol.insertOne((PrivateChat) conInfo);
    }

    protected void addConversationIdToUserAccount(long conversationId) {
        // TODO for #Matin
        // TODO go to accounts collection, create an array for conversation id if not exist and add this Id to array
    }

    protected Message fillMessageObj(NewMessage nM) {
        return new Message(nM.getConversationId(), nM.getSenderAccName(),
                nM.getReceiverAccName(), nM.getContent(), nM.getDate());
    }

    protected void saveMessage(Message message) {
        DBStuff.messageCol.insertOne(message);
    }

    protected boolean searchAccountsCollForLogin(LoginData loginData) {
        LoginData ld = DBStuff.accColByLoginData.find(eq("accountName", loginData.getAccountName())).first();
        return (ld != null) && (ld.getPassword().contentEquals(loginData.getPassword()));
    }


    protected void changeUserStatusTo(String accountName, UserStatus status) { // ONLINE or OFFLINE
        // TODO for #Matin
        // TODO go to accounts collection, find account using accountName and change status field

    }

    public synchronized long getId() {
        //TODO for #matin
        return new Date().getTime() / 1000;
    }
}

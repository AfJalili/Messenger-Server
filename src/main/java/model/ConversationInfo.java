package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * data transfer
 */
public class ConversationInfo implements Comparable<ConversationInfo>, Serializable {
    public final String GROUP_CHAT = "gp";
    public final String PRIVATE_CHAT = "pv";

    private long id;
    private String type; // pv / gp
    private Message lastMessage;
    private ArrayList<MemberInfo> members;
    private Date mongoDate;
    private String conversationPic = "";
    private String conversationName = "";


    public ConversationInfo(long id, String type, Message lastMessage, ArrayList<MemberInfo> members) {
        this.id = id;
        this.type = type;
        this.lastMessage = lastMessage;
        this.members = members;
    }

    public ConversationInfo(long id, String type, Message lastMessage, ArrayList<MemberInfo> members, Date mongoDate) {
        this.id = id;
        this.type = type;
        this.lastMessage = lastMessage;
        this.members = members;
        this.mongoDate = mongoDate;
    }

    public ConversationInfo(long id, String type, Message lastMessage, ArrayList<MemberInfo> members, Date mongoDate, String conversationPic) {
        this.id = id;
        this.type = type;
        this.lastMessage = lastMessage;
        this.members = members;
        this.mongoDate = mongoDate;
        this.conversationPic = conversationPic;
    }

    public ConversationInfo(long id, String type, Message lastMessage, ArrayList<MemberInfo> members, Date mongoDate, String conversationPic, String conversationName) {
        this.id = id;
        this.type = type;
        this.lastMessage = lastMessage;
        this.members = members;
        this.mongoDate = mongoDate;
        this.conversationPic = conversationPic;
        this.conversationName = conversationName;
    }

    public ConversationInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ArrayList<MemberInfo> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MemberInfo> members) {
        this.members = members;
    }

    public Date getMongoDate() {
        return mongoDate;
    }

    public void setMongoDate(Date mongoDate) {
        this.mongoDate = mongoDate;
    }

    public String getConversationPic() {
        return conversationPic;
    }

    public void setConversationPic(String conversationPic) {
        this.conversationPic = conversationPic;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }


    @Override
    public String toString() {
        return "ConversationInfo{" +
                "GROUP_CHAT='" + GROUP_CHAT + '\'' +
                ", PRIVATE_CHAT='" + PRIVATE_CHAT + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", lastMessage=" + lastMessage +
                ", members=" + members +
                ", mongoDate=" + mongoDate +
                ", conversationPic='" + conversationPic + '\'' +
                ", conversationName='" + conversationName + '\'' +
                '}';
    }

    @Override
    public int compareTo(ConversationInfo o) {
        return this.mongoDate.compareTo(o.mongoDate);
    }
}

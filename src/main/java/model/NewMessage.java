package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer object
 */
public class NewMessage implements Serializable {
    private long conversationId = 0;
    private String senderAccName;
    private String receiverAccName;
    private String content;
    private boolean containsFile;
    private java.util.Date Date;

    public NewMessage(long conversationId, String senderAccName, String content, boolean containsFile, java.util.Date date) {
        this.conversationId = conversationId;
        this.senderAccName = senderAccName;
        this.content = content;
        this.containsFile = containsFile;
        Date = date;
    }

    public NewMessage(long conversationId, String senderAccName, String receiverAccName, String content, java.util.Date date) {
        this.conversationId = conversationId;
        this.senderAccName = senderAccName;
        this.receiverAccName = receiverAccName;
        this.content = content;
        Date = date;
    }

    public NewMessage(long conversationId, String senderAccName, String receiverAccName, String content, boolean containsFile, java.util.Date date) {
        this.conversationId = conversationId;
        this.senderAccName = senderAccName;
        this.receiverAccName = receiverAccName;
        this.content = content;
        this.containsFile = containsFile;
        Date = date;
    }

    public NewMessage(String senderAccName, String receiverAccName, String content, java.util.Date date, boolean containsFile){
        this.senderAccName = senderAccName;
        this.receiverAccName = receiverAccName;
        this.content  = content;
        Date = date;
        this.containsFile = containsFile;
    }


    public long getConversationId() {
        return conversationId;
    }

    public boolean isContainsFile() {
        return containsFile;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public String getSenderAccName() {
        return senderAccName;
    }

    public void setSenderAccName(String senderAccName) {
        this.senderAccName = senderAccName;
    }

    public String getReceiverAccName() {
        return receiverAccName;
    }

    public void setReceiverAccName(String receiverAccName) {
        this.receiverAccName = receiverAccName;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean containsFile() {
        return containsFile;
    }

    public void setContainsFile(boolean containsFile) {
        this.containsFile = containsFile;

    }

    @Override
    public String toString() {
        return "NewMessage{" +
                "conversationId=" + conversationId +
                ", senderAccName='" + senderAccName + '\'' +
                ", receiverAccName='" + receiverAccName + '\'' +
                ", content='" + content + '\'' +
                ", containsFile=" + containsFile +
                ", Date=" + Date +
                '}';
    }
}

package model;

/**
 * Data Transfer object
 */
public class NewMessage {
    private long conversationId = 0;
    private String senderAccName;
    private String receiverAccName;
    private String content;
    private java.util.Date Date;

    public NewMessage(long conversationId, String senderAccName, String receiverAccName, String content, java.util.Date date) {
        this.conversationId = conversationId;
        this.senderAccName = senderAccName;
        this.receiverAccName = receiverAccName;
        this.content = content;
        Date = date;
    }

    public NewMessage(String senderAccName, String receiverAccName, String content, java.util.Date date) {
        this.senderAccName = senderAccName;
        this.receiverAccName = receiverAccName;
        this.content = content;
        Date = date;
    }


    public long getConversationId() {
        return conversationId;
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
}

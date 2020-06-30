package model;

import java.util.Date;

public class PrivateChat {
    private long chatId;  // using system nano time to create Id
    private String member1; // account name
    private String member2; // account name
    private Date lastMessageDate;
    private String lastMessageContent;

    public PrivateChat(long chatId, String member1, String member2, Date lastMessageDate, String lastMessageContent) {
        this.chatId = chatId;
        this.member1 = member1;
        this.member2 = member2;
        this.lastMessageDate = lastMessageDate;
        this.lastMessageContent = lastMessageContent;
    }

    public PrivateChat(long time, String senderAccName, String receiverAccName, String date, String content) {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getMember1() {
        return member1;
    }

    public void setMember1(String member1) {
        this.member1 = member1;
    }

    public String getMember2() {
        return member2;
    }

    public void setMember2(String member2) {
        this.member2 = member2;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }
}

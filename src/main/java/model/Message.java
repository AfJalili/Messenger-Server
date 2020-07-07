package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private long conversationId;
    private String sender;
    private String receiver = "empty";
    private String content;
    private Date date;
    private boolean seen = false;
    private boolean containsFile;

    public Message(long conversationId, String sender, String receiver, String content, Date date) {
        this.conversationId = conversationId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public Message(long conversationId, String sender, String receiver, String content, Date date, boolean seen, boolean containsFile) {
        this.conversationId = conversationId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
        this.seen = seen;
        this.containsFile = containsFile;
    }

    public Message() {
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean containsFile() {
        return containsFile;
    }

    public void setContainsFile(boolean containsFile) {
        this.containsFile = containsFile;
    }

    @Override
    public String toString() {
        return "Message{" +
                "conversationId=" + conversationId +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", seen=" + seen +
                ", containsFile=" + containsFile +
                '}';
    }
}

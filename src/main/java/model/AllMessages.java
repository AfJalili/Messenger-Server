package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AllMessages implements Serializable {
    private ArrayList<Message> allMessages;

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(ArrayList<Message> allMessages) {
        this.allMessages = allMessages;
    }

    public AllMessages(ArrayList<Message> allMessages) {
        this.allMessages = allMessages;
    }

    @Override
    public String toString() {
        return "AllMessages{" +
                "allMessages=" + allMessages +
                '}';
    }
}

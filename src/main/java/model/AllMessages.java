package model;

import java.util.ArrayList;

public class AllMessages {
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
}

package model;

import java.io.Serializable;

public class RequestConversation implements Serializable {
    public final long id;
    public RequestConversation (long id) {
        this.id = id;
    }
}

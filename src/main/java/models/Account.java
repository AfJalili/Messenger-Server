package models;

import org.bson.types.ObjectId;

import java.awt.*;
import java.util.ArrayList;

public class Account { // user info
    private String gender;
    private String AccountName;  //unique
    private String userName;
    private String password;
    private Image profilePic = null;
    private String status = "ONLINE";
    ArrayList<ObjectId> contacts;
    ArrayList<ObjectId> conversations;

    public Account() {
    }

    public Account(String gender, String accountName, String userName, String password, Image profilePic) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.password = password;
        this.profilePic = profilePic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ObjectId> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ObjectId> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<ObjectId> getConversations() {
        return conversations;
    }

    public void setConversations(ArrayList<ObjectId> conversations) {
        this.conversations = conversations;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }


}

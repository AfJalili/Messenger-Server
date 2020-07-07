package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data Transfer class
 */
public class UserInfo implements Serializable {
    private String gender;
    private String AccountName;  //unique
    private String userName;
    private String profilePic = "";
    private ArrayList<String> contactsAccName;
    private ArrayList<ConversationInfo> conversations;

    public UserInfo(String gender, String accountName, String userName, String profilePic, ArrayList<ConversationInfo> conversations) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.profilePic = profilePic;
        this.conversations = conversations;
    }

    public UserInfo(String gender, String accountName, String userName, String profilePic, ArrayList<String> contactsAccName, ArrayList<ConversationInfo> conversations) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.profilePic = profilePic;
        this.contactsAccName = contactsAccName;
        this.conversations = conversations;
    }

    public UserInfo(String gender, String accountName, String userName, ArrayList<ConversationInfo> conversations) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.conversations = conversations;
    }

    public UserInfo(String gender, String accountName, String userName, ArrayList<String> contactsAccName, ArrayList<ConversationInfo> conversations) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.contactsAccName = contactsAccName;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getContactsAccName() {
        return contactsAccName;
    }

    public void setContactsAccName(ArrayList<String> contactsAccName) {
        this.contactsAccName = contactsAccName;
    }

    public ArrayList<ConversationInfo> getConversations() {
        return conversations;
    }

    public void setConversations(ArrayList<ConversationInfo> conversations) {
        this.conversations = conversations;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "gender='" + gender + '\'' +
                ", AccountName='" + AccountName + '\'' +
                ", userName='" + userName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", contactsAccName=" + contactsAccName +
                ", conversations=" + conversations +
                '}';
    }
}

package model;


import java.awt.*;
/**
 * Data transfer Class
 */
public class NewAccount {

    private String gender;
    private String AccountName;  //unique
    private String userName;
    private String password;
    private Image profilePic = null;

    public NewAccount(String gender, String accountName, String userName, String password) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.password = password;
    }

    public NewAccount(String gender, String accountName, String userName, String password, Image profilePic) {
        this.gender = gender;
        AccountName = accountName;
        this.userName = userName;
        this.password = password;
        this.profilePic = profilePic;
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

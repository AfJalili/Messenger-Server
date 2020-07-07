package model;


import java.util.ArrayList;

public class Account { // user info
    private String gender;
    private String AccountName;  //unique
    private String userName;
    private String password;
    private String profilePic;
    private String status = "ONLINE";


    public Account() {
    }

    public Account(String gender, String accountName, String userName, String password, String profilePic) {
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }


}

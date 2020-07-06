package model;

import java.io.Serializable;

/**
 * data transfer
 */
public class MemberInfo implements Serializable {
    private String AccountName;
    private String UserName;
    private String profilePic = null;
    private String gender;

    public MemberInfo(String accountName, String userName, String profilePic) {
        AccountName = accountName;
        UserName = userName;
        this.profilePic = profilePic;
    }

    public MemberInfo(String accountName, String userName) {
        AccountName = accountName;
        UserName = userName;
    }

    public MemberInfo(String accountName, String userName, String profilePic, String gender) {
        AccountName = accountName;
        UserName = userName;
        this.profilePic = profilePic;
        this.gender = gender;
    }

    public MemberInfo() {
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "AccountName='" + AccountName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

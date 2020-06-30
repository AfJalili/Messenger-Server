package model;

/**
 * Data transfer Class
 */

public class AccNameAndProfilePic {
    private String accountName;
    // profilePic


    public AccNameAndProfilePic(String accountName) {
        this.accountName = accountName;
    }

    public AccNameAndProfilePic() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "AccNameAndProfilePic{" +
                "accountName='" + accountName + '\'' +
                '}';
    }
}

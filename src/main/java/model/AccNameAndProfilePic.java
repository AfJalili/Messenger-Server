package model;

import java.io.Serializable;

/**
 * Data transfer Class
 */

public class AccNameAndProfilePic implements Serializable {
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

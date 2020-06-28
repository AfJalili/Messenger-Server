package models;

public class LoginData {
    String accountName;
    String password;

    public LoginData() {
    }

    public LoginData(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

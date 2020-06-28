package server.db;

import models.LoginData;
import models.NewAccount;

public interface DbAccessObj {
    public void connectToDatabase();

    public Boolean createNewAccount(NewAccount newAccount);

    public Boolean checkLogin(LoginData loginData);
}

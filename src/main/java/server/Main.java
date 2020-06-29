package server;

import models.LoginData;
import models.NewAccount;
import server.db.DAO;
import server.db.DbAccessObj;

public class Main {
    public static void main(String[] args) {
        testCreateNewAccount();
        testCheckLogin();
    }

    public static void testCreateNewAccount() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.createNewAccount(new NewAccount("male", "afesh", "matin", "50ShadeS"));

    }

    public static void testCheckLogin() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.checkLogin(new LoginData("yas", "50ShadeS"));
    }
}

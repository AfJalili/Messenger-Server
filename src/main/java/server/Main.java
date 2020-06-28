package server;

import models.NewAccount;
import server.db.DAO;
import server.db.DbAccessObj;

public class Main {
    public static void main(String[] args) {
        testCreateNewAccount();
    }

    public static void testCreateNewAccount() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.connectToDatabase();
        dbAccessObj.createNewAccount(new NewAccount("male", "james", "matin", "50ShadeS"));
    }
}

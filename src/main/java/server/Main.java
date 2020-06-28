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
        dbAccessObj.createNewAccount(new NewAccount("male", "afshinjal", "matin", "50ShadeS"));
    }
}

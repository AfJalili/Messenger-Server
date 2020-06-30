package server;

import connection.ClientThread;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import server.db.DAO;
import server.db.DbAccessObj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
//        testCreateNewAccount();
//        testCheckLogin();
//        testMessageHandler();
        testWaitForClient();
//        testGetAllUsersInfo();

    }

    public static void testCreateNewAccount() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.createNewAccount(new NewAccount("male", "afesh", "matin", "50ShadeS"));

    }

    public static void testCheckLogin() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.checkLogin(new LoginData("yas", "50ShadeS"));
    }

    public static  void testMessageHandler() {
        DbAccessObj dbAccessObj = new DAO();
        dbAccessObj.MessageHandler(new NewMessage(1593522667, "afshin", "reza", "hi afshin", new Date()));
    }

    public static void testWaitForClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        int i = 0;
        while(true) {
            Socket client = serverSocket.accept();
            i++;
            System.out.println(i + " client connected");
            ClientThread clientThread = new ClientThread(client);
            Thread thread = new Thread(clientThread);
            thread.start();
        }
    }

    public static void testGetAllUsersInfo() {
        DbAccessObj dbAccessObj = new DAO();
        System.out.println(dbAccessObj.getAllUsersInfo().toString());
    }


}

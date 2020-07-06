package server;

import connection.ClientThread;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import server.db.DAO;
import server.db.DAOImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
//        testCreateNewAccount();
//        testCheckLogin();
//        testMessageHandler();
//        testGetAllUsersInfo();
        testWaitForClient();

    }

    public static void testCreateNewAccount() {
        DAO DAO = new DAOImpl();
        DAO.createNewAccount(new NewAccount("male", "afshin", "af", "50ShadeS"));
        DAO.createNewAccount(new NewAccount("male", "reza", "re", "50ShadeS"));
        DAO.createNewAccount(new NewAccount("male", "amatin", "ma", "50ShadeS"));

    }

    public static void testCheckLogin() {
        DAO DAO = new DAOImpl();
        DAO.checkLogin(new LoginData("yas", "50ShadeS"));
    }

    public static  void testMessageHandler() {
        DAO DAO = new DAOImpl();
//        DAO.messageHandler(new NewMessage( "afshin", "reza", "hi reza", new Date()));
//        DAO.messageHandler(new NewMessage( "matin", "reza", "hi reza", new Date()));
        DAO.messageHandler(new NewMessage(1593905714, "reza", "matin", "hi matin", new Date()));
//        DAO.messageHandler(new NewMessage(1593522667, "reza", "matin", "hi matin", new Date()));
    }

    public static void testWaitForClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        int i = 0;
        while(true) {
            Socket client = serverSocket.accept();
            i++;
            System.out.println(i + " client connected");
            ClientThread clientThread = new ClientThread(client);
            Thread thread = new Thread(clientThread);
            thread.setDaemon(true);
            thread.start();

        }
    }

    public static void testGetAllUsersInfo() {
        DAO DAO = new DAOImpl();
        System.out.println(DAO.getAllUsersInfo().toString());
    }


}

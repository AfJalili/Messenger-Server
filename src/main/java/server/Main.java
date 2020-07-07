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

public class Main {
    public static void main(String[] args) throws IOException {

        WaitForClient();

    }

    public static void WaitForClient() throws IOException {
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

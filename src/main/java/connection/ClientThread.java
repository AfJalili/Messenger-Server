package connection;

import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import model.RequestConversation;
import server.db.DAO;
import server.db.DAOImpl;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientThread implements Runnable {
    String onlineUserAccName;
    Socket clientSocket;
    ObjectInputStream oIStream;
    ObjectOutputStream oOStream;
    Object inputObject;
    BufferedInputStream bIS;
    BufferedOutputStream bOS;
    DAO DAO = new DAOImpl();

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            bOS = new BufferedOutputStream(clientSocket.getOutputStream());
            bIS = new BufferedInputStream(clientSocket.getInputStream());
            oOStream = new ObjectOutputStream(bOS);
            oOStream.flush();
            oIStream = new ObjectInputStream(bIS);

            while (clientSocket.isConnected()) {
                if (bIS.available() > 0) {
                    inputObject = oIStream.readObject();
                    System.out.println("input: " + inputObject.toString());
                    doService(inputObject);
                    inputObject = null;
                }
            }
            System.out.println("connection is closed!!!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("socket exception" + e.getMessage());
        }

        // todo if connection got closed a method must save its user last activity
    }

    void doService(Object inputObject) throws IOException {
        // call database method and get response object
        Object outObj = null;
        if (inputObject instanceof NewAccount) {

            NewAccount na = (NewAccount) inputObject;
            if (DAO.createNewAccount(na)) {
                this.onlineUserAccName = na.getAccountName();
                outObj = true;
            } else { outObj = false; }

        } else if (inputObject instanceof NewMessage) {

            outObj = DAO.messageHandler((NewMessage) inputObject);

        } else if (inputObject instanceof LoginData) {

            LoginData ld = (LoginData) inputObject;
            if (DAO.checkLogin((LoginData) inputObject)) {
                this.onlineUserAccName = ld.getAccountName();
                outObj = true;
            } else { outObj = false; }

        } else if (inputObject.toString().contains("1W: ")) {

            String str = inputObject.toString();
            this.onlineUserAccName = str.substring(4);
            listenerMode(onlineUserAccName);

        } else if (inputObject.toString().equals("setting up user profile view")) {

            outObj = DAO.getUserInfo(onlineUserAccName);

        } else if (inputObject.toString().contains("search: ")) {

            outObj = DAO.searchAccName(inputObject.toString().substring(8));

        } else if (inputObject instanceof RequestConversation) {

            outObj = DAO.getAllMessagesOfConversation((RequestConversation) inputObject);

        } else if (inputObject.toString().equalsIgnoreCase("logging out")); {

            loggingOut();

        }

        if (outObj != null) {
            oOStream.writeObject(outObj);
            oOStream.flush();
            System.out.println("output: " + outObj.toString());
            outObj = null;
        }
    }

    void listenerMode(String accName) {
        while (true) {
            NewMessage nM = DAO.newMessageListener(accName);
            if (nM != null) {
                try {
                    oOStream.writeObject(nM);
                    oOStream.flush();
                    nM = null;
                    if (bIS.available() > 0) break;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage() + " error sending new Message to client");
                }
            }
        }
    }

    void loggingOut() {
        //TODO clean the online account
        //TODO change user status and save user last activity
        //TODO switch off the listener mode
    }
}

package connection;

import model.Account;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import server.db.DAO;
import server.db.DbAccessObj;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    Account onlineAccount;
    Socket clientSocket;
    public ObjectInputStream oIStream;
    public ObjectOutputStream oOStream;
    Object inputObject;
    DbAccessObj dbAccessObj = new DAO();

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        // TODO
    }

    @Override
    public void run() {

        try {
            oIStream = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            oOStream = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (clientSocket.isConnected()) {
            try {
                inputObject = oIStream.readObject();
                if (inputObject != null) {
                    doService(inputObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        // (optional)todo if connection get closed a method must save its user last activity
    }



    void doService(Object inputObject) {
        // todo call database method and get response object
        Object outObj = null;
        // TODO doSomething
        if (inputObject instanceof NewAccount) {
            outObj = dbAccessObj.createNewAccount((NewAccount) inputObject);
        } else if (inputObject instanceof NewMessage) {
            outObj = dbAccessObj.MessageHandler((NewMessage) inputObject);
        } else if (inputObject instanceof LoginData) {
            outObj = dbAccessObj.checkLogin((LoginData) inputObject);
        }
        sendDataToClient(outObj);

    }

    protected void sendDataToClient(Object outputObject)  {
        try {
            if (outputObject != null)
            oOStream.writeObject(outputObject);
            oOStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR occurred while sending data to user : " + onlineAccount.getAccountName());
        }

    }
}

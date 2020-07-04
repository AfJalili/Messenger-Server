package connection;

import model.Account;
import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import server.db.DAO;
import server.db.DbAccessObj;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

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
        // TODO there is problem working with readObject() method. it cant read null
        BufferedInputStream bIS;
        BufferedOutputStream bOS;
        try {
            bOS = new BufferedOutputStream(clientSocket.getOutputStream());
            bIS = new BufferedInputStream(clientSocket.getInputStream());
            oOStream = new ObjectOutputStream(bOS);
            oOStream.flush();
            oIStream = new ObjectInputStream(bIS);

            while (clientSocket.isConnected()) {
                if (bIS.available() > 0) {
                    inputObject = oIStream.readObject();
                }
                if (inputObject != null) {
                    System.out.println(inputObject.toString());
                    doService(inputObject);
                    inputObject = null;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
            e.getMessage();
            System.out.println("socket exception");
        }

        // todo if connection get closed a method must save its user last activity
    }



    void doService(Object inputObject) throws IOException {
        // todo call database method and get response object
        Object outObj = null;
        if (inputObject instanceof NewAccount) {
            outObj = dbAccessObj.createNewAccount((NewAccount) inputObject);
        } else if (inputObject instanceof NewMessage) {
            outObj = dbAccessObj.MessageHandler((NewMessage) inputObject);
        } else if (inputObject instanceof LoginData) {
            outObj = dbAccessObj.checkLogin((LoginData) inputObject);
        }
        if (outObj != null)
        oOStream.writeObject(outObj);
        oOStream.flush();
//        sendDataToClient(outObj); // TODO this method does not work, it throws exception
        System.out.println(Objects.requireNonNull(outObj).toString());

        outObj = null;
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

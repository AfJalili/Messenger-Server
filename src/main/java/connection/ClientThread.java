package connection;

import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import server.db.DAO;
import server.db.DbAccessObj;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientThread implements Runnable {
    String onlineUserAccName;
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
                e.printStackTrace();
            e.getMessage();
            System.out.println("socket exception");
        }

        // todo if connection get closed a method must save its user last activity
    }



    void doService(Object inputObject) throws IOException {
        // call database method and get response object
        Object outObj = null;
        if (inputObject instanceof NewAccount) {
            NewAccount na = (NewAccount) inputObject;
            if (dbAccessObj.createNewAccount(na)) {
                this.onlineUserAccName = na.getAccountName();
                outObj = true;
            } else { outObj = false; }

        } else if (inputObject instanceof NewMessage) {
            outObj = dbAccessObj.messageHandler((NewMessage) inputObject);

        } else if (inputObject instanceof LoginData) {
            LoginData ld = (LoginData) inputObject;
            if (dbAccessObj.checkLogin((LoginData) inputObject)) {
                this.onlineUserAccName = ld.getAccountName();
                outObj = true;
            } else { outObj = false; }

        } else if (inputObject.toString().contains("1W: ")) {
            String str = inputObject.toString();
            this.onlineUserAccName = str.substring(4);
            listenerMode(onlineUserAccName);

        } else if (inputObject.toString().equals("setting up user profile view")) {
            outObj = dbAccessObj.getUserInfo(onlineUserAccName);

        } else if (inputObject.toString().contains("search: ")) {
            // TODO Search fo accountName
            System.out.println("calling search");
            outObj = dbAccessObj.searchAccName(inputObject.toString().substring(8));
        }

        if (outObj != null) {
            oOStream.writeObject(outObj);
            oOStream.flush();
        }
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
            System.out.println("ERROR occurred while sending data to user : ");
        }

    }

    void listenerMode(String accName) {
        while (true) {
            NewMessage nM = dbAccessObj.newMessageListener(accName);
            if (nM != null) {
                try {
                    oOStream.writeObject(nM);
                    oOStream.flush();
                    nM = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage() + " error sending new Message to client");
                }
            }
        }
    }


}

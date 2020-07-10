import model.LoginData;
import model.NewAccount;
import model.NewMessage;
import model.RequestConversation;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client_1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Socket s = new Socket("localhost", 8888);
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(bis);
//        oos.writeObject(new LoginData("matin", "pass"));
//        oos.writeObject(new NewAccount("female", "parsazzzz", "Ali Jalili","pass","556776878"));
//        oos.writeObject(new NewAccount("male", "matin", "Matin Vahedi","pass"));
//        oos.writeObject(new NewAccount("male", "reza", "Reza Fathi","pass"));
//        oos.writeObject(new NewMessage( "reza", "afshin", "hello there", new Date(), false));
//        oos.writeObject(new NewMessage( "reza", "matin", "hello there",new Date(), false));
//        oos.writeObject(new NewMessage( "afshin", "matin", "hello there",new Date(), false));
//        oos.writeObject(new NewMessage( 1594066866,"reza", "afshin", "hi", false, new Date()));
//        oos.writeObject(new NewMessage( 1594066868, "reza", "matin", "hi",false, new Date()));
//        oos.writeObject(new NewMessage( 1594066869,"afshin", "matin", "hi",false, new Date()));
//        oos.writeObject(new RequestConversation(1594251156));
        oos.writeObject("close connection");
        oos.flush();

//        oos.writeObject("search: reza");
//        oos.writeObject("search: yasin");
//        oos.flush();



        System.out.println(25);


        while (true) {

            if (bis.available() > 0) {
            System.out.println(ois.readObject().toString());
            }
        }
    }
}

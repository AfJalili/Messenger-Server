import model.LoginData;
import model.NewAccount;
import model.NewMessage;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Client_1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 8888);
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(bis);
        System.out.println(15);
        oos.writeObject(new LoginData("matin", "pass"));
//        oos.writeObject(new NewAccount("male", "afshin", "Afshin Jalili","pass"));
//        oos.writeObject(new NewAccount("male", "matin", "Matin Vahedi","pass"));
//        oos.writeObject(new NewAccount("male", "reza", "Reza Fathi","pass"));
//        oos.writeObject(new NewMessage( "reza", "afshin", "hello there", new Date(), false));
//        oos.writeObject(new NewMessage( "reza", "matin", "hello there",new Date(), false));
//        oos.writeObject(new NewMessage( "afshin", "matin", "hello there",new Date(), false));
//        oos.writeObject(new NewMessage( 1594062988,"reza", "afshin", "hi", false, new Date()));
//        oos.writeObject(new NewMessage( 1594062990, "reza", "matin", "hi",false, new Date()));
//        oos.writeObject(new NewMessage( 1594062991,"afshin", "matin", "hi",false, new Date()));
//        oos.writeObject("1W: matin");
//        oos.writeObject("setting up user profile view");
        oos.writeObject("search: reza");
        oos.writeObject("search: yasin");
        oos.flush();



        System.out.println(25);


        while (true) {
            if (bis.available() > 0) {
                System.out.println(ois.readObject().toString());
            }
        }



    }
}

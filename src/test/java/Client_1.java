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
        oos.writeObject(new LoginData("username", "password"));
        oos.writeObject(new LoginData("qwertyui", "54665879"));
        oos.writeObject(new NewAccount("male", "accname", "aName","pass"));
        oos.writeObject(new NewMessage(1593522667, "reza", "afshin", "helllo there", new Date()));
        oos.writeObject(new NewMessage( "matin", "afshin", "another Message", new Date()));
        oos.flush();



        System.out.println(25);


        while (true) {
            if (bis.available() > 0) {
                System.out.println(ois.readObject().toString());
            }
        }



    }
}

import model.NewMessage;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client_5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 8888);
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(bis);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            oos.writeObject(new NewMessage(1594066869, "afshin", "matin", str, false, new Date()));
            oos.flush();
            if (bis.available() > 0) {
                System.out.println(ois.readObject().toString());
            }

        }
    }
}

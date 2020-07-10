import java.io.*;
import java.net.Socket;

public class Client_2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 8888);
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();
        Socket s2 = new Socket("localhost", 8888);
        BufferedOutputStream bos2 = new BufferedOutputStream(s2.getOutputStream());
        ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
        oos2.flush();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(bis);
        BufferedInputStream bis2 = new BufferedInputStream(s2.getInputStream());
        ObjectInputStream ois2 = new ObjectInputStream(bis2);

        oos.writeObject("1W: matin");
        oos.flush();
        oos2.writeObject("1W: matin");
        oos2.flush();
        while (true) {
            if (bis.available() > 0) {
                System.out.println(ois.readObject().toString());
            }
        }
    }

}

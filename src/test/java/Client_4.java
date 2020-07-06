import java.io.*;
import java.net.Socket;

public class Client_4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 8888);
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.flush();
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        ObjectInputStream ois = new ObjectInputStream(bis);

        oos.writeObject("1W: afshin");
        oos.flush();
        while (true) {
            if (bis.available() > 0) {
                System.out.println(ois.readObject().toString());
            }
        }

    }
}

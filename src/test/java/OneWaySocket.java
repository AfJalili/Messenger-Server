import java.io.*;
import java.net.Socket;

public class OneWaySocket {

    private Socket socket = new Socket("localhost", 8888);
    BufferedOutputStream bOS = new BufferedOutputStream(socket.getOutputStream());
    ObjectOutputStream oOStream = new ObjectOutputStream(bOS);
    BufferedInputStream bIS = new BufferedInputStream(socket.getInputStream());
    ObjectInputStream oIStream = new ObjectInputStream(bIS);


    private static OneWaySocket instance;

    static {
        try {
            instance = new OneWaySocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OneWaySocket() throws IOException {
        oOStream.flush();
    }

    public static OneWaySocket getInstance() {
        return instance;
    }

    public void sendData(Object o) throws IOException {
        oOStream.writeObject(o);
        oOStream.flush();
    }

    public Object getData() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("getting data instance");
            if (this.bIS.available() > 0) {
                return oIStream.readObject();
            }

        }
    }


}



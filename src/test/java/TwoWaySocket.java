import java.io.*;
import java.net.Socket;

public class TwoWaySocket {

    private Socket socket = new Socket("localhost", 8888);
    BufferedOutputStream bOS = new BufferedOutputStream(socket.getOutputStream());
    ObjectOutputStream oOStream = new ObjectOutputStream(bOS);

    BufferedInputStream bIS = new BufferedInputStream(socket.getInputStream());
    ObjectInputStream oIStream = new ObjectInputStream(bIS);


    private static TwoWaySocket socket2;

    static {
        try {
            socket2 = new TwoWaySocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TwoWaySocket() throws IOException {
        oOStream.flush();
    }




    public static TwoWaySocket getSocket2() {
        return socket2;
    }
    void sendData(Object o) throws IOException {
        oOStream.writeObject(o);
        oOStream.flush();
    }

    Object getData() throws IOException, ClassNotFoundException {
        while (true) {
            if (this.bIS.available() > 0) {
                return oIStream.readObject();
            }
        }
    }



}


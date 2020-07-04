import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        Socket socket = ss.accept();
        System.out.println("client 1");
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        ObjectInputStream oos = new ObjectInputStream(bis);
    }
}

class  Client {
    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("localhost", 8888);

        Client client = new Client(new Socket("localhost", 8888));
    }


    Socket clientSocket;
    public ObjectInputStream oIStream;
    public ObjectOutputStream oOStream;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
        BufferedInputStream bIS;
        BufferedOutputStream bOS;
        try {
            bOS = new BufferedOutputStream(clientSocket.getOutputStream());
            bIS = new BufferedInputStream(clientSocket.getInputStream());
            oOStream = new ObjectOutputStream(bOS);
//            oOStream.flush();
            oIStream = new ObjectInputStream(bIS);
            System.out.println("success");
        } catch (IOException e) {
//                e.printStackTrace();
            e.getMessage();
            System.out.println("socket exception");
        }
    }

}

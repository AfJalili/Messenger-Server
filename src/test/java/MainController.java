import java.io.*;

public class MainController {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        OneWaySocket socket1 = OneWaySocket.getInstance();
        TwoWaySocket socket2 = TwoWaySocket.getSocket2();

        socket1.sendData("search: reza");
        socket2.sendData("search: matin");
        var c = new Client_2();
        System.out.println(" socket1 : " + socket1.getData().toString());
        System.out.println(" socket2 : " + socket2.getData().toString());
        Thread.sleep(2000);



    }
}

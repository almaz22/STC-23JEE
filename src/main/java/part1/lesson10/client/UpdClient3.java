package part1.lesson10.client;

import java.net.InetAddress;
import java.util.Scanner;

/**
 * UpdClient1
 *
 * @author Almaz_Kamalov
 */
public class UpdClient3 {

    public static void main(String[] args) throws Exception {
        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 4445;
//        System.out.println("Write your name: ");
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        ClientUdpImpl client3 = new ClientUdpImpl(username, serverAddress, serverPort);
        ClientUdpImpl client3 = new ClientUdpImpl("username3", serverAddress, serverPort);
    }

}

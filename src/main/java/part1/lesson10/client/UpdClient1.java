package part1.lesson10.client;

import java.net.InetAddress;
import java.util.Scanner;

/**
 * UpdClient1
 *
 * @author Almaz_Kamalov
 */
public class UpdClient1 {

    public static void main(String[] args) throws Exception {
        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 4445;
//        System.out.println("Write your name: ");
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        ClientUdpImpl client1 = new ClientUdpImpl(username, serverAddress, serverPort);
        ClientUdpImpl client1 = new ClientUdpImpl("username1", serverAddress, serverPort);
    }

}

package part1.lesson10.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * ClientUdpImpl - Реализация клиента
 * На входе принимаем нужные параметры для подключения к серверу, создаем сокет и подключаемся
 * На приеме создаем слушателя {@link ClientUdpListener}
 * Сообщения для отправки вводятся с консоли
 * Для отправки личного сообщения пользователя требуется ввести символ "@" и никнейм (example: @username)
 * Для выхода из чата ввести "quit"
 *
 * @author Almaz_Kamalov
 */
public class ClientUdpImpl {

    private final InetAddress serverAddress;
    private final int serverPort;

    /**
     * В констукторе создаем клиента, при вводе слова quit закрываем сокет
     * @param username - никнейм
     * @param serverAddress - адрес сервера
     * @param serverPort - порт сервера
     */
    public ClientUdpImpl(String username, InetAddress serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        try (DatagramSocket serverSocket = new DatagramSocket()) {
            // создаем коннект к серверу
            serverSocket.connect(serverAddress, serverPort);
            // создаем слушателя
            ClientUdpListener listener = new ClientUdpListener(serverSocket);
            new Thread(listener).start();
            // отправляем серверу никнейм
            sendMessage(serverSocket, username);

            // вводи сообщения в чат, пока не не напишем quit
            Scanner scanner = new Scanner(System.in);
            String message;
            do {
                message = scanner.nextLine();
                sendMessage(serverSocket, message);
            } while (!message.equals("quit"));

        } catch (SocketException e) {
            System.out.println("Socket not created");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("The message is not send");
            e.printStackTrace();
        }

    }

    /**
     * Метод отправки сообщения
     * @param socket - наш сокет отправки
     * @param message - сообщение для отправки
     * @throws IOException
     */
    void sendMessage (DatagramSocket socket, String message) throws IOException {
        byte[] messageBytes = message.getBytes();
        DatagramPacket dp = new DatagramPacket(
                messageBytes, messageBytes.length, serverAddress, serverPort);
        socket.send(dp);
    }

}

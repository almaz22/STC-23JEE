package part1.lesson10.server;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * UdpServer - UDP сервер
 * Все сообщения, которые приходят на сервер, отправляются всем пользователям с указанием имени отправителя
 * Первое сообщение от клиента содержит имя пользователя, сохраняется в список пользователей его адрес и никнейм
 * Личные сообщения отправляются конкретному пользователю
 * При выходе из чата пользователя всем пользователям чата приходит оповещение о выходе
 * В конструкторе указываем адрес сервера и порт для создания сокета
 * @socket - сокет сервера
 * @users - список пользователей
 * @address - адрес сервера
 * @port - порт сервера
 *
 * @author Almaz_Kamalov
 */
public class UdpServer {

    private DatagramSocket socket;
    private HashMap<SocketAddress, String> users;
    private final InetAddress address;
    private final int port;

    public UdpServer(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Запускаем сервер
     * @throws SocketException
     */
    void start() throws SocketException {
        users = new HashMap<>();
        socket = new DatagramSocket(port, address);

        try {
            while (!socket.isClosed()) {
                // получаем сообщение и парсим
                byte[] b = new byte[256];
                DatagramPacket packet = new DatagramPacket(b, b.length);
                socket.receive(packet);
                byte[] buf = packet.getData();
                String message = new String(buf).trim();
                System.out.println(message);
                SocketAddress socketAddress = packet.getSocketAddress();

                // если это первое сообщение от нового сокета,
                // сохраняем в список и оповещаем остальных о его подключении
                if (!users.containsKey(socketAddress)) {
                    users.put(socketAddress, message);
                    String greetingMessage = message.concat(" подключился к чату");
                    System.out.println(greetingMessage);
                    sendGreetingMessage(greetingMessage);
                } else {
                    // если это личное сообщение
                    if (message.startsWith("@")) {
                        sendPrivateMessage(message, socketAddress);
                    } else {
                        // если получаем оповещение о выходе, удаляем из списка пользователей и оповещаем остальных
                        if (message.equals("quit")) {
                            sendGoodByeMessage(socketAddress);
                        } else {
                            // в противном случае это сообщение для всех пользователей чата
                            sendToAll(message, socketAddress);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Оповещаем остальных о новом пользователе и сохраняем в список
     * @param message - текст оповещения
     * @throws IOException
     */
    void sendGreetingMessage(String message) throws IOException {
        Set<SocketAddress> addresses = users.keySet();
        for (SocketAddress address : addresses) {
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address);
            socket.send(packet);
        }
    }

    /**
     * Оповещаем и выходе пользователя из чата и удаляем его из списка
     * @param userSenderAddress - адрес покинувшего пользователя
     * @throws IOException
     */
    void sendGoodByeMessage(SocketAddress userSenderAddress) throws IOException {
        String username = users.get(userSenderAddress);
        users.remove(userSenderAddress);
        String notifyMessage = username.concat(" покинул чат");
        Set<SocketAddress> addresses = users.keySet();
        for (SocketAddress address : addresses) {
            DatagramPacket packet = new DatagramPacket(
                        notifyMessage.getBytes(), notifyMessage.getBytes().length, address);
            socket.send(packet);
        }
    }

    /**
     * Отправка личного сообщения
     * После символа "@" ожидаем никнейм адресата,
     * если его нет в списках пользователей, то отправляем как обычное сообщение всем участикам чата
     * если он есть в списках пользователей, сообщение адресуется отправителю и адресату
     * если отправитель указал себя адресатом, то сообщение отправляется только отправителю
     * @param message - текст сообщения
     * @param userSenderAddress - адрес отправляемого пользователя
     * @throws IOException
     */
    void sendPrivateMessage(String message, SocketAddress userSenderAddress) throws IOException {
        String userSender = users.get(userSenderAddress);
        String userReceiver = message.substring(1, message.indexOf(' '));
        SocketAddress userAddress = getUserAddress(userReceiver);
        if (userAddress != null) {
            String privateMessage = userSender + ": " + message.substring(userReceiver.length() + 2);
            DatagramPacket packetToUserSender = new DatagramPacket(
                    privateMessage.getBytes(), privateMessage.getBytes().length, userSenderAddress);
            socket.send(packetToUserSender);
            if (!userAddress.equals(userSenderAddress)) {
                DatagramPacket packetToUserReceiver = new DatagramPacket(
                        privateMessage.getBytes(), privateMessage.getBytes().length, userAddress);
                socket.send(packetToUserReceiver);
            }
        } else {
            sendToAll(message, userSenderAddress);
        }
    }

    /**
     * Отправка сообщения всем участникам
     * Каждому пользователю из списка отправляется сообщение с указанием отправителя
     * @param message - текст сообщения
     * @param userSenderAddress - адрес отправителя
     * @throws IOException
     */
    void sendToAll(String message, SocketAddress userSenderAddress) throws IOException {
        String username = users.get(userSenderAddress);
        String sendMessage = username + ": " + message;
        byte[] sendMessageBytes = sendMessage.getBytes();
        Set<SocketAddress> addresses = users.keySet();
        for (SocketAddress address : addresses) {
            DatagramPacket packet = new DatagramPacket(sendMessageBytes, sendMessageBytes.length, address);
            socket.send(packet);
        }
    }

    /**
     * Поиск по никнейму адреса сокета клиента
     * @param username - никнейм
     * @return - возврат адреса сокета, если не найден - то null
     */
    private SocketAddress getUserAddress(String username) {
        Set<Map.Entry<SocketAddress, String>> entrySet = users.entrySet();

        for (Map.Entry<SocketAddress, String> pair : entrySet) {
            if (username.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

}

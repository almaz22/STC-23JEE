package part1.lesson10.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * ClientUdpListener - Слушатель
 * На входе получаем сокет клиента
 * Все сообщения, которые приходят на сокет клиента, выводим на экран
 * Завершаем при закрытии сокета
 *
 * @author Almaz_Kamalov
 */
public class ClientUdpListener extends Thread {

    private final DatagramSocket socket;

    public ClientUdpListener(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                byte[] b = new byte[256];
                DatagramPacket packet = new DatagramPacket(b, b.length);
                socket.receive(packet);
                String message = new String(packet.getData()).trim();
                System.out.println(message.trim());
            }
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
        }
    }
}

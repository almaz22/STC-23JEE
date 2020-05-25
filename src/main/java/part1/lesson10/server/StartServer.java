package part1.lesson10.server;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * StartServer
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * Задание 2.  Усовершенствовать задание 1:
 * a.      добавить возможность отправки личных сообщений (unicast).
 * b.      добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 *
 * @author Almaz_Kamalov
 */
public class StartServer {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        UdpServer server = new UdpServer(InetAddress.getLocalHost(), 4445);
        server.start();
    }
}

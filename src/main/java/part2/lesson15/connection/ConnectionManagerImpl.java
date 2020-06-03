package part2.lesson15.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.Main_02_15_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerImpl
 *
 * @author Almaz_Kamalov
 */
public class ConnectionManagerImpl implements ConnectionManager {

    private Logger logger = LogManager.getLogger(ConnectionManagerImpl.class);
    private static final ConnectionManager INSTANCE = new ConnectionManagerImpl();

    public static ConnectionManager getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "qwerty");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("ConnectionManagerImpl: " + e);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            logger.error("ConnectionManagerImpl: " + e);
        }
        return connection;
    }
}

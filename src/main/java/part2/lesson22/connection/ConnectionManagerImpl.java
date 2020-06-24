package part2.lesson22.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerImpl
 *
 * @author Almaz_Kamalov
 */
@EJB
@MyConnect
public class ConnectionManagerImpl implements ConnectionManager {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionManagerImpl.class);
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
                    "jdbc:postgresql://postgres:5432/postgres",
                    "postgres",
                    "qwerty");
        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
            LOGGER.error("ConnectionManagerImpl: " + e);
        }
        return connection;
    }
}

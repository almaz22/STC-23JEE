package part2.lesson15.connection;

import java.sql.Connection;

/**
 * ConnectionManager
 *
 * @author Almaz_Kamalov
 */
public interface ConnectionManager {
    Connection getConnection();
}

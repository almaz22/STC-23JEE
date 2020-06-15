package part2.lesson15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.dao.RoleDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * UsingSavepoint
 *
 * @author Almaz_Kamalov
 */
public class UsingSavepoint {
    private final Connection connection;

    private static final Logger LOGGER = LogManager.getLogger(UsingSavepoint.class);

    public UsingSavepoint(Connection connection) {
        this.connection = connection;
    }

    void useSavepoint(int idRoleA, int idRoleB) {
        Savepoint savepoint = null;
        RoleDAOImpl roleDAO = new RoleDAOImpl(connection);
        try{
            connection.setAutoCommit(false);
            LOGGER.info("Disable Auto Commit");
            roleDAO.addRole(idRoleA, "Role A"); // commit to DB
            savepoint = connection.setSavepoint("AddRoleA");
            LOGGER.info("Savepoint Role A");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            LOGGER.error(throwables);
        }
        try{
            roleDAO.addRole(idRoleB, "Role B"); // commit to DB
            savepoint = connection.setSavepoint("AddRoleB");
            LOGGER.info("Savepoint Role B");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            LOGGER.error(throwables);
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    LOGGER.info("Rollback to savepoint");
                } catch (SQLException e) {
//                    e.printStackTrace();
                    LOGGER.error(throwables);
                }
            }
        }
        finally {
            try {
                connection.commit();
                LOGGER.info("Commit");
                connection.setAutoCommit(true);
                LOGGER.info("Enable Auto Commit");
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                LOGGER.error(throwables);
            }
        }
    }
}

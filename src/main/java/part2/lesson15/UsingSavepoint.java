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

    private Logger logger = LogManager.getLogger(UsingSavepoint.class);

    public UsingSavepoint(Connection connection) {
        this.connection = connection;
    }

    void useSavepoint(int idRoleA, int idRoleB) {
        Savepoint savepoint = null;
        RoleDAOImpl roleDAO = new RoleDAOImpl(connection);
        try{
            connection.setAutoCommit(false);
            logger.info("Disable Auto Commit");
            roleDAO.addRole(idRoleA, "Role A"); // commit to DB
            savepoint = connection.setSavepoint("AddRoleA");
            logger.info("Savepoint Role A");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
        }
        try{
            roleDAO.addRole(idRoleB, "Role B"); // commit to DB
            savepoint = connection.setSavepoint("AddRoleB");
            logger.info("Savepoint Role B");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    logger.info("Rollback to savepoint");
                } catch (SQLException e) {
//                    e.printStackTrace();
                    logger.error(throwables);
                }
            }
        }
        finally {
            try {
                connection.commit();
                logger.info("Commit");
                connection.setAutoCommit(true);
                logger.info("Enable Auto Commit");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

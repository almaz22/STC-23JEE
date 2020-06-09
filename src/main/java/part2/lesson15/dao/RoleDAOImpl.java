package part2.lesson15.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleDAOImpl
 *
 * @author Almaz_Kamalov
 */
public class RoleDAOImpl implements RoleDAO {
    private final Connection connection;
    private final Logger logger = LogManager.getLogger(RoleDAOImpl.class);

    public static final String SELECT_ROLES = "SELECT id, description FROM roles";
    public static final String INSERT_ROLE = "INSERT INTO roles VALUES (?, ?)";
    public static final String DELETE_ROLE = "DELETE FROM roles WHERE id = ?";

    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ROLES);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setDescription(resultSet.getString(2));
                roles.add(role);
            }
            logger.info("Get Roles");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error("RoleDAOImpl: " + throwables);
        }
        return roles;
    }

    @Override
    public boolean addRole(int id, String description) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_ROLE)) {
            statement.setInt(1, id);
            statement.setString(2, description);
            statement.executeUpdate();
            logger.info("Add Role");
            return true;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error("RoleDAOImpl: " + throwables);
            return false;
        }
    }

    @Override
    public boolean removeRole(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ROLE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Remove Role");
            return true;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error("RoleDAOImpl: " + throwables);
            return false;
        }
    }
}

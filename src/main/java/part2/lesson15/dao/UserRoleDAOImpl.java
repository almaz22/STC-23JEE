package part2.lesson15.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserRoleDAOImpl
 *
 * @author Almaz_Kamalov
 */
public class UserRoleDAOImpl implements UserRoleDAO{
    private final Connection connection;

    public static final String SELECT_ALL_USER_ROLES = "SELECT id, user_id, role_id FROM user_roles";
    public static final String INSERT_USER_ROLE = "INSERT INTO user_roles VALUES (?, ?, ?)";
    public static final String UPDATE_USER_ROLE = "UPDATE user_roles SET role_id = ? WHERE user_id = ?";

    private final Logger logger = LogManager.getLogger(UserRoleDAOImpl.class);

    public UserRoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserRole> getUserRoles() {
        List<UserRole> userRoles = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_ROLES);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getLong(1));
                userRole.setUserId(resultSet.getLong(2));
                userRole.setRoleId(resultSet.getInt(3));
                userRoles.add(userRole);
            }
            logger.info("Get User Roles");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
        }
        return userRoles;
    }

    @Override
    public boolean addUserRole(UserRole userRole) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE)) {
            statement.setLong(1, userRole.getId());
            statement.setLong(2, userRole.getUserId());
            statement.setInt(3, userRole.getRoleId());
            statement.executeUpdate();
            logger.info("Add User Role");
            return true;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
            return false;
        }
    }

    @Override
    public boolean changeUserRole(long userId, int roleId) {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setInt(1, roleId);
            statement.setLong(2, userId);
            statement.executeUpdate();
            logger.info("Update User Role");
            return true;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
            return false;
        }
    }
}

package part2.lesson15.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAOImpl
 *
 * @author Almaz_Kamalov
 */
public class UserDAOImpl implements UserDAO{

    private final Connection connection;
    public static final String SELECT_ALL_USERS = "SELECT id, name, role, phone, email FROM users";
    public static final String SELECT_USER = "SELECT id, name, role, phone, email FROM users WHERE id = ? AND name = ?";
    public static final String INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    private final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setRole(resultSet.getInt(3));
                user.setPhone(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                users.add(user);
            }
            logger.info("Get Users");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
        }
        return users;
    }

    @Override
    public User getUser(long id, String name) {
        User user = new User();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            statement.setLong(1, id);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setRole(resultSet.getInt(3));
                user.setPhone(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
            }
            logger.info("Get User");
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error(throwables);
        }
        return user;
    }

    @Override
    public boolean addUsers(List<User> users) {
        if (!users.isEmpty()) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
                for (User user : users) {
                    statement.setString(1, user.getName());
                    statement.setInt(2, user.getRole());
                    statement.setString(3, user.getPhone());
                    statement.setString(4, user.getEmail());
                    statement.addBatch();
                }
                statement.executeBatch();
                logger.info("Add Users");
                return true;
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                logger.error(throwables);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        if (user != null) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getRole());
                statement.setString(3, user.getPhone());
                statement.setString(4, user.getEmail());
                statement.executeUpdate();
                logger.info("Add User");
                return true;
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                logger.error(throwables);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeUsers(List<User> users) {
        if (!users.isEmpty()) {
            try(PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
                for (User user : users) {
                    statement.setLong(1, user.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
                logger.info("Remove Users");
                return true;
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                logger.error(throwables);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        if (user != null) {
            try(PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
                statement.setLong(1, user.getId());
                statement.executeUpdate();
                logger.info("Remove User");
                return true;
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
                logger.error(throwables);
                return false;
            }
        }
        return false;
    }
}

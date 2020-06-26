package part2.lesson22.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson22.connection.ConnectionManager;
import part2.lesson22.connection.MyConnect;
import part2.lesson22.pojo.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDaoJdbcImpl
 *
 * @author Almaz_Kamalov
 */
@EJB
public class UserDaoJdbcImpl implements UserDao{
    private static final Logger LOGGER = LogManager.getLogger(UserDaoJdbcImpl.class);
    public static final String INSERT_INTO_USERNAME = "INSERT INTO username values (DEFAULT, ?, ?)";
    public static final String SELECT_FROM_USERNAME = "SELECT * FROM username WHERE id = ?";
    public static final String SELECT_FROM_USERNAME_BY_NAME = "SELECT * FROM username t WHERE t.username = ?";
    public static final String SELECT_ALL_FROM_USERNAME = "SELECT * FROM username";
    public static final String UPDATE_USERNAME = "UPDATE username SET username = ?, password = ? WHERE id = ?";
    public static final String DELETE_FROM_USERNAME = "DELETE FROM username WHERE id=?";
    public static final String CREATE_TABLE_USERNAME
            = "DROP TABLE IF EXISTS username;\n"
            + "create table username\n"
            + "(\n"
            + "    id bigserial not null\n"
            + "        constraint username_pkey\n"
            + "            primary key,\n"
            + "    username varchar(100) unique,\n"
            + "    password varchar(100) not null\n"
            + ");\n"
            + "\n"
            + "alter table username owner to postgres;";

    private final ConnectionManager connectionManager;

    @Inject
    public UserDaoJdbcImpl(@MyConnect ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERNAME)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in addMobile method", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(Integer id, String username, String password) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERNAME)) {
            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Some thing wrong in updateUser method", throwables);
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USERNAME)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            LOGGER.error("Some thing wrong in deleteUser method", throwables);
        }
        return false;
    }

    @Override
    public User getUserById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERNAME)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getUserById method", e);
        }
        return null;
    }

    @Override
    public User getUserByName(String username) {
        System.out.println("getUserByName " + username);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERNAME_BY_NAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getUserById method", e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERNAME);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getAllUsers method", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void createTable() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USERNAME)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in createTable method", e);
        }
    }
}

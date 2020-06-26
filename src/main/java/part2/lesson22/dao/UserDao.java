package part2.lesson22.dao;

import part2.lesson22.pojo.User;

import java.util.List;

/**
 * UserDao
 *
 * @author Almaz_Kamalov
 */
public interface UserDao {
    boolean addUser(User user);
    boolean updateUser(Integer id, String username, String password);
    boolean deleteUser(Integer id);
    User getUserById(Integer id);
    User getUserByName(String username);
    List<User> getAllUsers();
    void createTable();
}

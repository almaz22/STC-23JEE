package part2.lesson15.dao;

import part2.lesson15.entity.User;

import java.util.List;

/**
 * UserDAO
 *
 * @author Almaz_Kamalov
 */
public interface UserDAO {
    List<User> getUsers();
    User getUser(long id, String name);

    boolean addUsers(List<User> users);
    boolean addUser(User user);

    boolean removeUsers(List<User> users);
    boolean removeUser(User user);

}

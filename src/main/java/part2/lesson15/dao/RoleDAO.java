package part2.lesson15.dao;

import part2.lesson15.entity.Role;

import java.util.List;

/**
 * RoleDAO
 *
 * @author Almaz_Kamalov
 */
public interface RoleDAO {
    List<Role> getRoles();

    boolean addRole(int id, String description);

    boolean removeRole(int id);
}

package part2.lesson15.dao;

import part2.lesson15.entity.UserRole;

import java.util.List;

/**
 * UserRoleDAO
 *
 * @author Almaz_Kamalov
 */
public interface UserRoleDAO {
    List<UserRole> getUserRoles();

    boolean addUserRole(UserRole userRole);

    boolean changeUserRole(long userId, int roleId);
}

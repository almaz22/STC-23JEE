package part2.lesson15.dao;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import part2.lesson15.connection.ConnectionManagerImpl;
import part2.lesson15.entity.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.*;
import java.util.List;

/**
 * RoleDAOImplTest
 *
 * @author Almaz_Kamalov
 */
class RoleDAOImplTest {
    private static final Logger LOGGER = LogManager.getLogger(RoleDAOImplTest.class);

    private RoleDAOImpl roleDAO;
    private Connection connection;

    @BeforeEach
    void setUp() {
        LOGGER.trace("BeforeEach in RoleDAOImplTest");
        initMocks(this);
        connection = spy(ConnectionManagerImpl.getINSTANCE().getConnection());
        roleDAO = spy(new RoleDAOImpl(connection));
    }

    @AfterEach
    void tearDown() throws SQLException {
        LOGGER.trace("AfterEach in RoleDAOImplTest");
        connection.close();
    }

    @Test
    @DisplayName("Кол-во ролей в БД больше нуля")
    void getRoles() {
        LOGGER.trace("Test getRoles in RoleDAOImplTest");

        List<Role> roles = roleDAO.getRoles();
        assertTrue(roles.size() > 0);
    }

    @Test
    @DisplayName("Кол-во ролей в БД больше на 1 после добавления роли")
    void addRole() {
        LOGGER.trace("Test addRole in RoleDAOImplTest");

        int size = roleDAO.getRoles().size();
        boolean result = roleDAO.addRole(10, "test role");
        assertTrue(result);
        assertEquals(size + 1, roleDAO.getRoles().size());
    }

    @Test
    @DisplayName("Кол-во ролей в БД меньше на 1 после удаления роли")
    void removeRole() {
        LOGGER.trace("Test removeRole in RoleDAOImplTest");

        int size = roleDAO.getRoles().size();
        roleDAO.removeRole(10);
        assertEquals(size - 1, roleDAO.getRoles().size());
    }
}
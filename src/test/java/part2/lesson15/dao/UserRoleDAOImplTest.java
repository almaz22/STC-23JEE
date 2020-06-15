package part2.lesson15.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import part2.lesson15.entity.UserRole;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UserRoleDAOImplTest
 *
 * @author Almaz_Kamalov
 */
class UserRoleDAOImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserRoleDAOImplTest.class);

    private UserRoleDAOImpl userRoleDAO;
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() {
        LOGGER.trace("setUp in UserRoleDAOImplTest");
        initMocks(this);
        connection  = mock(Connection.class);
        userRoleDAO = spy(new UserRoleDAOImpl(connection));
    }

    @AfterEach
    void tearDown() {
        LOGGER.trace("tearDown in UserRoleDAOImplTest");
    }

    @Test
    void initializeUserRoleDAOImpl() {
        LOGGER.trace("initializeUserRoleDAOImpl in UserRoleDAOImplTest");
        assumeTrue(connection != null);
        assertDoesNotThrow(() -> new UserRoleDAOImpl(connection));
    }

    @Test
    void getUserRoles() throws SQLException {
        LOGGER.trace("getUserRoles in UserRoleDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserRoleDAOImpl.SELECT_ALL_USER_ROLES);
        doReturn(resultSetMock).when(preparedStatement).executeQuery();
        when(resultSetMock.next()).thenReturn(true,false);
        when(resultSetMock.getLong(1)).thenReturn(1L);
        when(resultSetMock.getLong(2)).thenReturn(1L);
        when(resultSetMock.getInt(3)).thenReturn(1);

        List<UserRole> userRoles = userRoleDAO.getUserRoles();
        assertAll("assert all",
                () -> assertEquals(1L, userRoles.get(0).getId()),
                () -> assertEquals(1L, userRoles.get(0).getUserId()),
                () -> assertEquals(1, userRoles.get(0).getRoleId()));

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    void addUserRole() throws SQLException {
        LOGGER.trace("addUserRole in UserRoleDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserRoleDAOImpl.INSERT_USER_ROLE);

        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserId(1L);
        userRole.setRoleId(1);

        boolean result = userRoleDAO.addUserRole(userRole);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void changeUserRole() throws SQLException {
        LOGGER.trace("changeUserRole in UserRoleDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserRoleDAOImpl.UPDATE_USER_ROLE);

        boolean result = userRoleDAO.changeUserRole(1L, 2);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
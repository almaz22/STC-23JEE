package part2.lesson15.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import part2.lesson15.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UserDAOImplTest
 *
 * @author Almaz_Kamalov
 */
class UserDAOImplTest {
    private static final Logger logger = LogManager.getLogger(UserDAOImplTest.class);

    private UserDAOImpl userDAO;
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() {
        logger.trace("setUp in UserDAOImplTest");
        initMocks(this);
        connection  = mock(Connection.class);
        userDAO = spy(new UserDAOImpl(connection));
    }

    @AfterEach
    void tearDown() {
        logger.trace("tearDown in UserDAOImplTest");
    }

    @Test
    void initializeUserDAOImpl() {
        logger.trace("initializeUserDAOImpl in UserDAOImplTest");
        assumeTrue(connection != null);
        assertDoesNotThrow(() -> new UserDAOImpl(connection));
    }

    @Test
    void getUsers() throws SQLException {
        logger.trace("getUsers in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.SELECT_ALL_USERS);
        doReturn(resultSetMock).when(preparedStatement).executeQuery();
        when(resultSetMock.next()).thenReturn(true,false);
        when(resultSetMock.getLong(1)).thenReturn(1L);
        when(resultSetMock.getString(2)).thenReturn("name");
        when(resultSetMock.getInt(3)).thenReturn(1);
        when(resultSetMock.getString(4)).thenReturn("phone");
        when(resultSetMock.getString(5)).thenReturn("email");

        List<User> users = userDAO.getUsers();
        assertAll("assert all",
                () -> assertEquals(1L, users.get(0).getId()),
                () -> assertEquals("name", users.get(0).getName()),
                () -> assertEquals(1, users.get(0).getRole()),
                () -> assertEquals("phone", users.get(0).getPhone()),
                () -> assertEquals("email", users.get(0).getEmail()));

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    void getUser() throws SQLException {
        logger.trace("getUser in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.SELECT_USER);
        doReturn(resultSetMock).when(preparedStatement).executeQuery();
        when(resultSetMock.next()).thenReturn(true,false);
        when(resultSetMock.getLong(1)).thenReturn(1L);
        when(resultSetMock.getString(2)).thenReturn("name");
        when(resultSetMock.getInt(3)).thenReturn(1);
        when(resultSetMock.getString(4)).thenReturn("phone");
        when(resultSetMock.getString(5)).thenReturn("email");

        User user = userDAO.getUser(1L, "name");
        assertAll("assert all",
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("name", user.getName()),
                () -> assertEquals(1, user.getRole()),
                () -> assertEquals("phone", user.getPhone()),
                () -> assertEquals("email", user.getEmail()));

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    void addUsers() throws SQLException {
        logger.trace("addUsers in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.INSERT_USER);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setRole(1);
        user.setPhone("phone");
        user.setEmail("email");
        users.add(user);

        boolean result = userDAO.addUsers(users);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeBatch();
    }

    @Test
    void addUser() throws SQLException {
        logger.trace("addUser in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.INSERT_USER);

        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setRole(1);
        user.setPhone("phone");
        user.setEmail("email");

        boolean result = userDAO.addUser(user);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void removeUsers() throws SQLException {
        logger.trace("removeUsers in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.DELETE_USER);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setRole(1);
        user.setPhone("phone");
        user.setEmail("email");
        users.add(user);

        boolean result = userDAO.removeUsers(users);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeBatch();
    }

    @Test
    void removeUser() throws SQLException {
        logger.trace("removeUser in UserDAOImplTest");
        doReturn(preparedStatement).when(connection).prepareStatement(UserDAOImpl.INSERT_USER);

        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setRole(1);
        user.setPhone("phone");
        user.setEmail("email");

        boolean result = userDAO.addUser(user);

        assertTrue(result);
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
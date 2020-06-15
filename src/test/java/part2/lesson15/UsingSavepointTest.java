package part2.lesson15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part2.lesson15.connection.ConnectionManagerImpl;
import part2.lesson15.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UsingSavepointTest
 *
 * @author Almaz_Kamalov
 */
class UsingSavepointTest {
    private static final Logger LOGGER = LogManager.getLogger(UsingSavepointTest.class);

    private UsingSavepoint savepoint;
    private Connection connection;

    @BeforeAll
    static void reNewDataBase() throws SQLException {
        LOGGER.trace("UsingSavepointTest: Recreate database");
        Connection connection = ConnectionManagerImpl.getINSTANCE().getConnection();
        DBUtil.newDatabase(connection);
        connection.close();
    }

    @BeforeEach
    void setUp() {
        LOGGER.trace("UsingSavepointTest: setUp");
        initMocks(this);
        connection = spy(ConnectionManagerImpl.getINSTANCE().getConnection());
        savepoint = spy(new UsingSavepoint(connection));
    }

    @AfterEach
    void tearDown() throws SQLException {
        LOGGER.trace("UsingSavepointTest: tearDown");
        connection.close();
    }

    @Test
    void useSavepointWithoutRollback() throws SQLException {
        LOGGER.trace("UsingSavepointTest: useSavepointWithoutRollback");
        savepoint.useSavepoint(3,4);
        verify(connection, never()).rollback(any());
    }

    @Test
    void useSavepointWithRollback() throws SQLException {
        LOGGER.trace("UsingSavepointTest: useSavepointWithRollback");
        savepoint.useSavepoint(5,5);
        verify(connection, atLeastOnce()).rollback(any());
    }

    @Test
    void throwSqlException() {
//        assertThrows(SQLException.class, () -> savepoint.useSavepoint(0,0));

        NullPointerException nullPointerException = assertThrows(NullPointerException.class,
                () -> new UsingSavepoint(null).useSavepoint(100, 100));
        assertNull(nullPointerException.getMessage());

    }
}
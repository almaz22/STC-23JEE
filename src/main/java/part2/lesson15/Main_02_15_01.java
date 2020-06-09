package part2.lesson15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.lesson15.connection.ConnectionManager;
import part2.lesson15.connection.ConnectionManagerImpl;
import part2.lesson15.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main_02_15_01
 * Задание 1. Взять за основу предметную область выбранную на занятии по UML:
 *      Спроектировать базу данных для выбранной предметной области (минимум три таблицы).
 *      Типы и состав полей в таблицах на ваше усмотрение.
 *      Связи между таблицами делать не обязательно.
 *
 * Задание 2. Через JDBC интерфейс описать CRUD операции с созданными таблицами:
 *      Применить параметризованный запрос.
 *      Применить батчинг.
 *      Использовать ручное управление транзакциями.
 *      Предусмотреть использование savepoint при выполнении логики из нескольких запросов.
 *      Предусмотреть rollback операций при ошибках.
 *      Желательно предусмотреть метод сброса и инициализации базы данных.
 *
 * @author Almaz_Kamalov
 */
public class Main_02_15_01 {

    private static final Logger logger = LogManager.getLogger(Main_02_15_01.class);
    private static final ConnectionManager connectionManager = ConnectionManagerImpl.getINSTANCE();

    public static void main(String[] args) {

        logger.debug("Main_02_15_01 is started");
        try (Connection connection = connectionManager.getConnection()) {
            logger.debug("Recreate database");
            DBUtil.newDatabase(connection);
            logger.debug("Database created");
            logger.debug("Using Savepoint");
            UsingSavepoint usingSavepoint = new UsingSavepoint(connection);
            logger.debug("Savepoint initialized");

            // Используем savepoint
            // Если withoutTroubleInSavepoint = true, то без rollback записываем данные,
            // в противном случае возникнет ошибка при записи и будет использован rollback
            boolean withoutTroubleInSavepoint = true;
            if (withoutTroubleInSavepoint) {
                logger.debug("Use savepoint without trouble");
                usingSavepoint.useSavepoint(3,4);
            } else {
                logger.debug("Use savepoint with trouble");
                usingSavepoint.useSavepoint(3, 3);
            }

        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            logger.error("Catch error in Main: " + throwables);
        }
    }
}

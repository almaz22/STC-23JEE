package part2.lesson15.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBUtil
 *
 * @author Almaz_Kamalov
 */
public class DBUtil {

    public static void newDatabase(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("-- Database: Create initial data\n" +
                    "DROP TABLE IF EXISTS users;\n" +
                    "\n" +
                    "CREATE TABLE users (\n" +
                    "    id bigserial primary key,\n" +
                    "    name varchar(100) NOT NULL,\n" +
                    "    role integer NOT NULL,\n" +
                    "    phone varchar(12) NOT NULL,\n" +
                    "    email varchar(100) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO users (name, role, phone, email)\n" +
                    "VALUES\n" +
                    "   ('Charles Xavier', 1, '+79111111111', 'ProfessorX@mail.ru'),\n" +
                    "   ('Eric Lensherr', 1, '+79111111111', 'Magneto@mail.ru'),\n" +
                    "   ('Logan', 2, '+79009998877', 'Wolverine@mail.ru'),\n" +
                    "   ('Peter Parker', 2, '+79100002233', 'Spidy@mail.ru'),\n" +
                    "   ('Wade Wilson', 2, '+79997778866', 'Deadpool@mail.ru'),\n" +
                    "   ('Raven Darkholme', 2, '+79008889922', 'Mystic@mail.ru');\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS roles;\n" +
                    "\n" +
                    "CREATE TABLE roles (\n" +
                    "    id integer primary key,\n" +
                    "    description varchar(100)\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO roles (id, description)\n" +
                    "VALUES\n" +
                    "    (0, 'Administrator'),\n" +
                    "    (1, 'Professor'),\n" +
                    "    (2, 'Student');" +
                    "\n" +
                    "DROP TABLE IF EXISTS user_roles;\n" +
                    "\n" +
                    "CREATE TABLE user_roles (\n" +
                    "                       id bigserial primary key,\n" +
                    "                       user_id integer NOT NULL,\n" +
                    "                       role_id integer NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO user_roles (user_id, role_id)\n" +
                    "VALUES\n" +
                    "    (1, 1),\n" +
                    "    (2, 1),\n" +
                    "    (3, 2),\n" +
                    "    (4, 2),\n" +
                    "    (5, 2),\n" +
                    "    (6, 2);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

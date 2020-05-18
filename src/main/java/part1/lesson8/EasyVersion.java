package part1.lesson8;

import java.io.*;

/**
 * EasyVersion
 * Необходимо разработать класс, реализующий следующие методы: *
 * void serialize (Object object, String file); *
 * Object deSerialize(String file); *
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 *
 * @author Almaz_Kamalov
 */
public class EasyVersion {
    /**
     * Метод сериализации
     * @param object объект сериализации
     * @param file файл, куда сериализуем
     */
    private static void serialize(Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод десериализации
     * @param file откуда берем сериализуемый объект
     * @return возвращаем десериализованный объект
     */
    private static Object deSerialize(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

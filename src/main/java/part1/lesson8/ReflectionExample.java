package part1.lesson8;

import part1.lesson02.task03.Person;
import part1.lesson04.Pet;

/**
 * ReflectionExample
 *
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 *
 * @author Almaz_Kamalov
 */

public class ReflectionExample {

    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person(20, Person.Sex.MAN, "Tom");
        Pet pet = new Pet("id", "Blansh", person, 3);

        String fileName = "src/main/resources/lesson8/result.txt";

        InnoContext context = new InnoContext();
        context.serialize(pet, fileName);

        Pet newPet = (Pet) context.deSerialize(fileName);

        System.out.println(newPet.equals(pet));

    }
}

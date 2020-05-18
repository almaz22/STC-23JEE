package part1.lesson8;

import part1.lesson02.task03.Person;
import part1.lesson04.task01.Pet;
import part1.lesson7.FactorialNumber;
import part1.lesson7.Main_01_07_01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;

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


    public static void main(String[] args) throws IllegalAccessException, JAXBException {

        Person person = new Person(20, Person.Sex.MAN, "Tom");
        Pet pet = new Pet("id", "Blansh", person, 3);
//        printFields(pet);
//        serialize(pet, "src\\main\\resources\\lesson8\\result.txt");
        deSerialize("src\\main\\resources\\lesson8\\result.txt");

/*
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(person, writer);

        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);

        StringReader reader = new StringReader(result);

        JAXBContext context1 = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();

        Person person1 = (Person) unmarshaller.unmarshal(reader);
//        Pet pet1 = (Pet) unmarshaller.unmarshal(reader);*/
    }

    private static void printFields(Object obj) throws IllegalAccessException {
        System.out.println(obj.getClass().getName());
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field declaredField : fields) {
            System.out.print(
                    //Modifier.toString(declaredField.getModifiers()) + " " +
                            //declaredField.getType().getSimpleName() + " " +
                            declaredField.getName() + ": ");
            declaredField.setAccessible(true); // доступ к приватному полю
            System.out.println(declaredField.get(obj));
        }
    }

    static void serialize (Object object, String file) throws IllegalAccessException {
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8))) {
            Field[] fields = object.getClass().getDeclaredFields();
            StringBuilder text = new StringBuilder();
            text.append(object.getClass().getName()).append("\n");
            for (Field declaredField : fields) {
                //text.append(Modifier.toString(declaredField.getModifiers())).append(" ");
                //text.append(declaredField.getType().getSimpleName()).append(" ");
                text.append(declaredField.getName()).append(": ");
                declaredField.setAccessible(true); // доступ к приватному полю
                text.append(declaredField.get(object)).append("\n");
            }
            writer.write(text.toString(), 0, text.length());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Object deSerialize(String file) {
        Pet pet = null;
        Object obj = null;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {
            String objectName = reader.readLine();

            /*Class<?> clazz = Class.forName(objectName);
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    obj = clazz.newInstance();
                    break;
                } else {
                    int parameterCount = constructor.getParameterCount();
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    System.out.println(parameterTypes[0].getName());

                    String line; int count = 0;
                    while ((line = reader.readLine()) != null) {
//                        String type = parameterTypes[count].getName();

                        String[] values = line.split(":");
                        String value = values[1];
                    }

                    Object[] ob = new Object[]{"id", "Blansh", null, 3};
                    obj = clazz.getConstructor(parameterTypes).newInstance(ob);

                    break;
                }
            }
            System.out.println(constructors[0].getParameterCount());
//            Object obj = clazz.newInstance();
            printFields(obj);*/

//            pet = (Pet) clazz.newInstance();


//            printFields(pet);

        } catch (IOException /*| ClassNotFoundException | IllegalAccessException
                | InstantiationException | InvocationTargetException | NoSuchMethodException*/ e) {
            e.printStackTrace();
        }
        return null;
    }

    Object createArgs (Class<?> cl, String line)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Object obj = null;

        String[] strings = line.split(":");
        if (strings.length > 2) {
            String[] str = line.split(":", 2);
            createArgs(cl, str[1]);
        }

        String objectName = cl.getName();
        Class<?> clazz = Class.forName(objectName);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                obj = clazz.newInstance();
                break;
            } else {
                int parameterCount = constructor.getParameterCount();
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                System.out.println(parameterTypes[0].getName());

                int count = 0;

                Object[] ob = new Object[]{"id", "Blansh", null, 3};
                obj = clazz.getConstructor(parameterTypes).newInstance(ob);

                break;
            }
        }
        System.out.println(constructors[0].getParameterCount());
//            Object obj = clazz.newInstance();
        printFields(obj);

        return obj;
    }
}

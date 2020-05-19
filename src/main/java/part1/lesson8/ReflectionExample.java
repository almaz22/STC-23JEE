package part1.lesson8;

import part1.lesson02.task03.Person;
import part1.lesson04.task01.Pet;
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


    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person(20, Person.Sex.MAN, "Tom");
        Pet pet = new Pet("id", "Blansh", person, 3);
//        printFields(pet);
//        serialize(pet, "src\\main\\resources\\lesson8\\result.txt");
        serialize(pet, "src/main/resources/lesson8/result.txt");
        Object deserializedObject = deSerialize( "src/main/resources/lesson8/result.txt");
        System.out.println(deserializedObject.getClass());
//        deSerialize("src\\main\\resources\\lesson8\\result.txt");

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
            String text = createFields(object);
            writer.write(text, 0, text.length());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Object deSerialize(String file) {
        Object obj = null;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {
            String objectName = reader.readLine();
            obj = fillObject(reader, objectName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    static Object fillObject (BufferedReader reader, String objectName) {
        Object resultObject = null;
        try {
            Object object = createObject(objectName);
            assert object != null;
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field declaredField : fields) {
                String[] line = reader.readLine().split(":");
                if (line.length > 1) {
                    String value = line[1];
                    if (declaredField.getType().isPrimitive()) {
                        declaredField.setAccessible(true);
                        declaredField.set(object, toObject(declaredField.getType(), value));
                    } else {
                        if (declaredField.getType().equals(String.class)) {
                            declaredField.setAccessible(true);
                            declaredField.set(object, value);
                        }
                        // Надо добавить массивы, коллекции и прочие ссылочные классы
                    }
                } else {
                    String objName = reader.readLine();
                    Object objValue = fillObject(reader, objName);
                    declaredField.setAccessible(true);
                    declaredField.set(object, objValue);
                }
            }
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    public static Object toObject( Class<?> clazz, String value ) {
        if( Boolean.class == clazz ) return Boolean.parseBoolean( value );
        if( Byte.class == clazz ) return Byte.parseByte( value );
        if( Short.class == clazz ) return Short.parseShort( value );
        if( Integer.class == clazz ) return Integer.parseInt( value );
        if( Long.class == clazz ) return Long.parseLong( value );
        if( Float.class == clazz ) return Float.parseFloat( value );
        if( Double.class == clazz ) return Double.parseDouble( value );
        if( boolean.class == clazz ) return Boolean.parseBoolean( value );
        if( byte.class == clazz ) return Byte.parseByte( value );
        if( short.class == clazz ) return Short.parseShort( value );
        if( int.class == clazz ) return Integer.parseInt( value );
        if( long.class == clazz ) return Long.parseLong( value );
        if( float.class == clazz ) return Float.parseFloat( value );
        if( double.class == clazz ) return Double.parseDouble( value );
        return value;
    }

    static String createFields(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder text = new StringBuilder();
        text.append(object.getClass().getName()).append("\n");

        // Условие с массивом нужно доработать
        for (Field declaredField : fields) {
            if (declaredField.getType().isPrimitive()
                    || declaredField.getType().equals(String.class)
                    || declaredField.getType().isEnum()) {
                text.append(declaredField.getName()).append(":");
                declaredField.setAccessible(true);
                text.append(declaredField.get(object)).append("\n");
            } else {
                if(!object.getClass().isArray()) {
                    text.append(declaredField.getName()).append(":");
                    text.append("\n");
                    declaredField.setAccessible(true);
                    text.append(createFields(declaredField.get(object)));
                }
            }
        }

        return text.toString();
    }

    static Object createObject (String objectName)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName(objectName);
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors)
            if (constructor.getParameterCount() == 0) {
                return clazz.newInstance();
            } else {
                int parameterCount = constructor.getParameterCount();
                Object[] parameters = new Object[parameterCount];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                for (int i = 0; i < parameterCount; i++) {
                    parameters[i] = parameterTypes[i].isPrimitive() ? 0 : null;
                }
                return clazz.getConstructor(parameterTypes).newInstance(parameters);
            }
        return null;
    }
}

package part1.lesson8;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class InnoContext {

    void serialize (Object object, String file) throws IllegalAccessException {
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8))) {
            String text = context(object);
            writer.write(text, 0, text.length());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    String context(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder text = new StringBuilder();
        text.append(object.getClass().getName()).append("\n");

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
                    text.append(context(declaredField.get(object)));
                }
            }
        }

        return text.toString();
    }

    Object deSerialize(String file) {
        Object obj = null;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {
            String objectName = reader.readLine();
            obj = newInstance(reader, objectName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    Object newInstance(BufferedReader reader, String objectName) {
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
                        } /*else {
                            if (declaredField.getType().isEnum()) {
                                declaredField.setAccessible(true);
                                declaredField.set(object, value);
                            }
                        }*/
                    }
                } else {
                    String objName = reader.readLine();
                    Object objValue = newInstance(reader, objName);
                    declaredField.setAccessible(true);
                    declaredField.set(object, objValue);
                }
            }
            resultObject = object;
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    Object createObject (String objectName)
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

    Object toObject( Class<?> clazz, String value ) {
        if( boolean.class == clazz ) return Boolean.parseBoolean( value );
        if( byte.class == clazz ) return Byte.parseByte( value );
        if( short.class == clazz ) return Short.parseShort( value );
        if( int.class == clazz ) return Integer.parseInt( value );
        if( long.class == clazz ) return Long.parseLong( value );
        if( float.class == clazz ) return Float.parseFloat( value );
        if( double.class == clazz ) return Double.parseDouble( value );
        return value;
    }

}

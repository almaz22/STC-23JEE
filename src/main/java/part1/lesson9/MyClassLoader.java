package part1.lesson9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс реализации собственного ClassLoader
 */
public class MyClassLoader extends ClassLoader {

    String PATH = Main_01_09_01.SOME_CLASS_PATH;
    String PACKAGE = Main_01_09_01.PACKAGE;

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ((PACKAGE + "." + "SomeClass").equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        System.out.println("SomeClass starts work: " + name);
        if ((PACKAGE + "." + "SomeClass").equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(PATH + "SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}

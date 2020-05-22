package part1.lesson9;

/**
 * Необходимо написать программу, выполняющую следующее:
 *
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork()
 * в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Main_01_09_01 {

    public static final String PACKAGE = "part1.lesson9";
    public static final String SOME_CLASS_NAME = "SomeClass";
    public static final String SOME_CLASS_PATH = "src/main/java/part1/lesson9/";

    public static void main(String[] args) {
        SomeClassGenerator someClassGenerator = new SomeClassGenerator();
        boolean generateClassSuccess = someClassGenerator.generateSomeClass(PACKAGE, SOME_CLASS_NAME, SOME_CLASS_PATH);
        try {
            if (generateClassSuccess) {
                useMyClassLoader();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализации собственного ClassLoader
     */
    private static void useMyClassLoader()
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = new MyClassLoader();
        Class<?> someClass = cl.loadClass(PACKAGE + "." + SOME_CLASS_NAME);
        Worker worker = (Worker) someClass.newInstance();
        worker.doWork();
    }

}

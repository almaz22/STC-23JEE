package part1.lesson02.task01;

/**
 * NullPointerExceptionDemo
 * Класс для моделирования ошибки «NullPointerException».
 *
 * @author Almaz_Kamalov
 */
public class NullPointerExceptionDemo {

    private String helloWorld;
    public String getHelloWorld() {
        return helloWorld;
    }

    public NullPointerExceptionDemo() {
    }

    public NullPointerExceptionDemo(String helloWorld) {
        this.helloWorld = helloWorld;
    }

    /**
     * Метод предназначен для моделирования ошибки NPE, вызвав Getter с пустым значением
     * и реализацией любого метода класса String. Таким образом мы получим ошибку NPE.
     * @return - возвращаем переменную helloWorld
     */
    String testNPE () {
        return getHelloWorld().toUpperCase();
    }

    public static void main(String[] args)  {
        // Неверная реализация
        NullPointerExceptionDemo nullPointerExceptionDemo = new NullPointerExceptionDemo();
        System.out.println(nullPointerExceptionDemo.testNPE());

        // Верная реализация
        NullPointerExceptionDemo nullPointerExceptionDemo1 = new NullPointerExceptionDemo("Hello, World!");
        System.out.println(nullPointerExceptionDemo1.testNPE());
    }

}

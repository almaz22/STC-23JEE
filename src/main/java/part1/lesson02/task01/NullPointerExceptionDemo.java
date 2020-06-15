package part1.lesson02.task01;

import java.util.Scanner;

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

    public void myRuntimeExceptionDemo() {
        Scanner in = new Scanner(System.in);
        System.out.println("Say: \"Hello, World!\"");
        String helloWorld = in.nextLine();

        if (!helloWorld.toLowerCase().contains("hello"))
            throw new RuntimeException("Не поздоровался!");
        System.out.println("Hello, World!");
    }
}

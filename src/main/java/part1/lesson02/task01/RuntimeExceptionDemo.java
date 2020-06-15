package part1.lesson02.task01;

import java.util.Scanner;

/**
 * RuntimeExceptionDemo - Вызвав свой вариант ошибки через оператор throw
 *
 * @author Almaz_Kamalov
 */
public class RuntimeExceptionDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Say: \"Hello, World!\"");
        String helloWorld = in.nextLine();

        if (!helloWorld.toLowerCase().contains("hello"))
            throw new RuntimeException("Не поздоровался!");
        System.out.println("Hello, World!");
    }
}

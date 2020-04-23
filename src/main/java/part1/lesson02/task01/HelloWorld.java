package part1.lesson02.task01;

import java.util.Scanner;

/**
 * HelloWorld
 * Класс для моделирования собственной ошибки через оператор throw.
 * В данном классе пользователя попросят написать "Hello, World!", и если он не поздоровается, то это будет ошибкой
 *
 * @author Almaz_Kamalov
 */
public class HelloWorld {

    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);
        System.out.println("Say: \"Hello, World!\"");
        String helloWorld = in.nextLine();

        if (!helloWorld.contains("hello") && !helloWorld.contains("Hello"))
            throw new RuntimeException("Не поздоровался!");
        System.out.println("Hello, World!");
    }
}

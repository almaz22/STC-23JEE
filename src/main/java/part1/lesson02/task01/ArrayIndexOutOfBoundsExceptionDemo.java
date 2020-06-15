package part1.lesson02.task01;

/**
 * ArrayIndexOutOfBoundsExceptionDemo
 * Класс для моделирования ошибки «ArrayIndexOutOfBoundsException».
 *
 * @author Almaz_Kamalov
 */
public class ArrayIndexOutOfBoundsExceptionDemo {

    public static void main(String[] args)  {

        String helloWorld = "Hello, World!";
        char[] chars = helloWorld.toCharArray();

        // Неверная реализация из-за обращения к элементу массива по превыщающему размера массива индексу
        for (int i = 0; i < chars.length + 1; i++) {
            System.out.print(chars[i]);
        }

        // Верная реализация
        for (char aChar : chars) {
            System.out.print(aChar);
        }
    }
}
package part1.lesson11;

import java.util.stream.Stream;

/**
 * Main_01_11_01
 * Пример использования лямдба и Stream API на ДЗ_3.
 * Рассматриваем варианты уникальных элементов из массива, деление всех элементов на число,
 * суммирование всех элементов, массив без элемента.
 *
 * @author Almaz_Kamalov
 */
public class Main_01_11_01 {
    public static void main(String[] args) {
        Number[] numbers = new Number[] {100, 10, 50, 20, 25, 115, 22, 43, 27, 17};

        System.out.println("Массив без дубликатов:");
        Stream.of(numbers)
                .distinct()
                .forEach(System.out::println);

        int splitter = 5;
        System.out.println("Массив после деления на число " + splitter + ":");
        Stream.of(numbers)
                .distinct()
                .map(x -> x.intValue() / splitter)
                .forEach(System.out::println);

        int deleted = 100;
        System.out.println("Массив без элемента " + deleted + ":");
        Stream.of(numbers)
                .distinct()
                .filter(x -> x.intValue() != deleted)
                .forEach(System.out::println);

        Stream.of(numbers)
                .distinct()
                .mapToInt(Number::intValue)
                .reduce(Integer::sum)
                .ifPresent(var -> System.out.println("Сумма всех элементов: " + var));
    }
}

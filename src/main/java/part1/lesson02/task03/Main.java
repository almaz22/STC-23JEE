package part1.lesson02.task03;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static part1.lesson02.task03.Person.Sex.*;

/**
 * Main
 * Программа генерирует массив элементов из объектов Person.
 * Отсортировывает массив по следующим условиям:
 * - первые идут мужчины
 * - выше в списке тот, кто более старший
 * - имена сортируются по алфавиту
 * Выводит на экран отсортированный список и время работы каждого алгоритма сортировки.
 *
 * @author Almaz_Kamalov
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();

        // Создаем 10000 элементов объекта Person
        for (int i = 0; i < 10000; i++) {
            Person.Sex sex = randomSex();
            persons.add(new Person(randomAge(), sex, randomName(sex)));
        }

        // Сортируем по алгоритму "Пузырька"
        BubbleSort bubbleSort = new BubbleSort(persons);
        long startTime = System.currentTimeMillis();
        bubbleSort.sort();
        long bubbleSortTime = System.currentTimeMillis() - startTime;
        bubbleSort.print();

        // Сортируем по алгоритму "Быстрой сортировки"
        QuickSort quickSort = new QuickSort(persons);
        startTime = System.currentTimeMillis();
        quickSort.sort();
        long quickSortTime = System.currentTimeMillis() - startTime;
        quickSort.print();

        startTime = System.currentTimeMillis();
        persons.sort(new PersonComparator());
        long comparatorSortTime = System.currentTimeMillis() - startTime;
        for (Person p: persons) {
            System.out.println(p.toString());
        }

        System.out.println("Время сортировки пузырьком: " + bubbleSortTime);
        System.out.println("Время быстрой сортировки: " + quickSortTime);
        System.out.println("Время сортировки с помощью Comparator-а: " + comparatorSortTime);

    }

    /**
     * Генерация случайного возраста
     * @return - возвращает случайное число от 0 до 100
     */
    public static int randomAge() {
        return new Random().nextInt(100);
    }

    /**
     * Генерация случайного пола, вероятность 50/50
     * @return - возвращает мужской или женский пол
     */
    public static Person.Sex randomSex() {
        return new Random().nextInt(2) == 0 ? MAN : WOMAN;
    }

    /**
     * Генерируем случайное имя в зависимости от пола, мужское или женское
     * @param sex - пол, мужской или женский
     * @return - возврашает имя
     */
    public static String randomName(Person.Sex sex) {
        int rnd = new Random().nextInt(100);
        String fileName = sex == MAN ? "src\\main\\resources\\maleNames" : "src\\main\\resources\\femaleNames";

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName),
                        StandardCharsets.UTF_8))) {
            String line; int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == rnd)
                    return line;
                i++;
            }
        } catch (IOException e) {
            System.out.println("Файл " + fileName + " не был найден в resources");
        }

        return null;
    }
}

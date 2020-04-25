package part1.lesson02.task03;

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
        ArrayList<Person> persons = new ArrayList<Person>();

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
        Collections.sort(persons, new PersonComparator());
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
    static int randomAge() {
        return new Random().nextInt(100);
    }

    /**
     * Генерация случайного пола, вероятность 50/50
     * @return - возвращает мужской или женский пол
     */
    static Person.Sex randomSex() {
        return new Random().nextInt(2) == 0 ? MAN : WOMAN;
    }

    /**
     * Генерируем случайное имя в зависимости от пола, мужское или женское
     * @param sex - пол, мужской или женский
     * @return - возврашает имя
     */
    static String randomName(Person.Sex sex) {
        String[] maleNames = new String[] {
                "Игнатий",
                "Николай",
                "Потап",
                "Богдан",
                "Артём",
                "Кир",
                "Онуфрий",
                "Даниил",
                "Радислав",
                "Семён",
                "Константин",
                "Николай",
                "Леонид",
                "Кондрат",
                "Анатолий",
                "Валентин",
                "Лавр",
                "Дмитрий",
                "Тимур",
                "Варфоломей",
                "Казимир",
                "Лука",
                "Александр",
                "Виктор",
                "Рюрик",
                "Семён",
                "Михаил",
                "Роман",
                "Александр",
                "Ираклий",
                "Николай",
                "Ипполит",
                "Андрей",
                "Лев",
                "Александр",
                "Роман",
                "Максим",
                "Кузьма",
                "Максим",
                "Кирилл",
                "Гавриил",
                "Семён",
                "Сергей",
                "Иван",
                "Всеволод",
                "Трофим",
                "Максим",
                "Радислав",
                "Никита",
                "Дмитрий",
                "Фока",
                "Вячеслав",
                "Тимур",
                "Прохор",
                "Ярослав",
                "Семён",
                "Степан",
                "Евгений",
                "Ян",
                "Игорь",
                "Егор",
                "Руслан",
                "Гавриил",
                "Богдан",
                "Степан",
                "Аркадий",
                "Вадим",
                "Владимир",
                "Мирослав",
                "Якуб",
                "Роман",
                "Тарас",
                "Потап",
                "Петр",
                "Варфоломей",
                "Юлиан",
                "Иван",
                "Игорь",
                "Вадим",
                "Парфен",
                "Степан",
                "Макар",
                "Георгий",
                "Святослав",
                "Павел",
                "Артур",
                "Елисей",
                "Сергей",
                "Мартын",
                "Вадим",
                "Тимур",
                "Андрон",
                "Александр",
                "Павел",
                "Юрий",
                "Вадим",
                "Никита",
                "Самуил",
                "Алексей",
                "Денис"
        };
        String[] femaleNames = new String[] {
                "Анастасия",
                "Зоя",
                "Наталия",
                "Зоя",
                "Валентина",
                "Полина",
                "Владлена",
                "Дарья",
                "Софья",
                "Анна",
                "Надежда",
                "Светлана",
                "Алина",
                "Алиса",
                "Виктория",
                "Маргарита",
                "Валентина",
                "Юлия",
                "Раиса",
                "Алиса",
                "Валентина",
                "Ксения",
                "Диана",
                "Дарья",
                "Рада",
                "Юлия",
                "Злата",
                "Нина",
                "Ксения",
                "Кира",
                "Елизавета",
                "Рената",
                "Элеонора",
                "Виктория",
                "Яна",
                "Евгения",
                "Светлана",
                "Ирина",
                "Виктория",
                "Вера",
                "Марта",
                "Евгения",
                "Мария",
                "Наталия",
                "Алина",
                "Валентина",
                "Анастасия",
                "Евгения",
                "Галина",
                "Светлана",
                "Елена",
                "Лилия",
                "Нина",
                "Екатерина",
                "Ярослава",
                "Марина",
                "Элеонора",
                "Арина",
                "Евгения",
                "Нина",
                "Мария",
                "Наталья",
                "Светлана",
                "Ангелина",
                "Ольга ",
                "Агата",
                "Изабелла",
                "Арина",
                "Ирина",
                "Марина",
                "Дарья",
                "Евгения",
                "Юлия",
                "Марина",
                "Альбина",
                "Вероника",
                "Ульяна",
                "Алина",
                "Анна",
                "Ольга",
                "Оксана",
                "Нина",
                "Софья",
                "Ольга",
                "Виктория",
                "Евгения",
                "Вероника",
                "Алиса",
                "Каролина",
                "Лариса",
                "Валентина",
                "Наталья",
                "Елена",
                "Надежда",
                "Виктория",
                "Владлена",
                "Марина",
                "Варвара",
                "Екатерина",
                "Виктория"
        };
        return sex == MAN ?
                maleNames[new Random().nextInt(maleNames.length)] :
                femaleNames[new Random().nextInt(femaleNames.length)];
    }
}

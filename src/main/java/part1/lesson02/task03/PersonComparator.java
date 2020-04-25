package part1.lesson02.task03;

import java.util.Comparator;

/**
 * PersonComparator - Класс Comparator, сортирует по гендерному признаку, сначала мужчины,
 * далее сортируем по старшенству, после сортируем по алфавиту.
 *
 * @author Almaz_Kamalov
 */
public class PersonComparator implements Comparator<Person> {
    public int compare(Person o1, Person o2) {

        if (o1.age == o2.age && o1.name.equals(o2.name))
            throw new RuntimeException("Имя и возраст одинаковое: " + o1.name + "  " + o1.age );

        // Сравниваем по полу, сначала мужчины
        int result = o1.sex.compareTo(o2.sex);
        // Если пол одинаковый, сравниваем по старшенству, старшие выше
        if(result == 0)
            result = o2.age - o1.age;
        // Если пол и возраст одинаковый, сортируем по алфавиту
        if(result == 0)
            result = o1.name.compareTo(o2.name);
        return result;
    }
}

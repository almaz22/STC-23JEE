package part1.lesson02.task03;

import java.util.ArrayList;

/**
 * BubbleSort - класс сортировки "Пузырьком".
 * Сначала сравнивается по гендерному признаку, сначала мужчины.
 * Далее сравниваем по старшенству.
 * И после сортируем по алфавиту.
 *
 * @author Almaz_Kamalov
 */
public class BubbleSort implements Sort {

    private final ArrayList<Person> people;

    public BubbleSort(ArrayList<Person> people) {
        this.people = people;
    }

    /**
     * Вызов метода сортировки "Пузырьком"
     */
    public void sort() {
        if (!people.isEmpty()) {
            for (int i = 0; i + 1 < people.size(); i++) {
                for (int j = 0; j + 1 < people.size() - i; j++) {
                    if (people.get(j + 1).sex.getSex() < people.get(j).sex.getSex()) {
                        swap(j, j + 1);
                    } else {
                        if (people.get(j + 1).sex.getSex() == people.get(j).sex.getSex() &&
                                people.get(j + 1).age > people.get(j).age) {
                            swap(j, j + 1);
                        } else {
                            if (people.get(j + 1).sex.getSex() == people.get(j).sex.getSex() &&
                                    people.get(j + 1).age == people.get(j).age &&
                                    people.get(j + 1).name.compareTo(people.get(j).name) < 0) {
                                swap(j, j + 1);
                            } else {
                                if (people.get(j + 1).sex.getSex() == people.get(j).sex.getSex() &&
                                        people.get(j + 1).age == people.get(j).age &&
                                        people.get(j + 1).name.compareTo(people.get(j).name) == 0) {
                                throw new RuntimeException(
                                        "Имя и возраст одинаковое: " + people.get(j).name + "  " + people.get(j).age);
//                                    System.out.println(
//                                            "Имя и возраст одинаковое: " + people.get(j).name + " " + people.get(j).age);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Метод смены позиций в массиве
     * @param i - позиция 1
     * @param j - позиция 2
     */
    private void swap(int i, int j) {
        Person person = this.people.get(j);
        this.people.set(j, this.people.get(i));
        this.people.set(i, person);
    }

    public void print() {
        for (Person p: people) {
            System.out.println(p.toString());
        }
    }
}

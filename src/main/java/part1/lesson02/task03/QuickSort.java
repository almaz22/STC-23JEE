package part1.lesson02.task03;

import java.util.ArrayList;

/**
 * QuickSort - класс "Быстрой сортировки"
 * Сначала сравнивается по гендерному признаку, сначала мужчины.
 * Далее сравниваем по старшенству.
 * И после сортируем по алфавиту.
 *
 * Этот алгоритм состоит из трёх шагов. Сначала из массива нужно выбрать один элемент — его обычно называют опорным.
 * Затем другие элементы в массиве перераспределяют так, чтобы элементы меньше опорного оказались до него,
 * а большие или равные — после. А дальше рекурсивно применяют первые два шага
 * к подмассивам справа и слева от опорного значения.
 *
 * @author Almaz_Kamalov
 */
public class QuickSort implements SortImpl {

    private final ArrayList<Person> people;

    public QuickSort(ArrayList<Person> people) {
        this.people = people;
    }

    /**
     * Метод сравнения, если элемент "a" меньше, чем элемент "b", то отвечаем да.
     * В нашем случае меньше - это сначала мужчины, самые старшие и отсортированные по алфавиту.
     * @param a - персона 1
     * @param b - персона 2
     * @return - возращаем результат сравнения
     */
    boolean toCompare(Person a, Person b) {
//        System.out.println("Сравниваем: " + a.toString() + " && " + b.toString() );
        if (a.sex.getSex() < b.sex.getSex()) {
            return true;
        } else {
            if (a.sex.getSex() == b.sex.getSex()) {
                if (a.age > b.age) {
                    return true;
                } else {
                    if (a.age == b.age) {
                        if (a.name.compareTo(b.name) < 0) {
                            return true;
                        } else {
                            if (a.name.compareTo(b.name) == 0) {
//                                System.out.println("Имя и возраст одинаковое: " + a.name + "  " + a.age);
                                throw new RuntimeException("Имя и возраст одинаковое: " + a.name + "  " + a.age);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Метод сортировки элементов.
     * Все элементы подмассива меньшие, чем элемент с индексом end (опорный), встают в начало подмассива.
     * Таким образом, все элементы, большие опорного элемента, смещаются к концу массива.
     * После прохода подмассива, опорный элемент встает на позицию после всех меньших или равных элементов.
     * @param people - подмассив элементов
     * @param begin - начало подмассива
     * @param end - конец подмассива и индекс опорного элемента
     * @return - возвращает позицию опорного элемента после сортировки
     */
    int partition(ArrayList<Person> people, int begin, int end) {
        Person p = people.get(end);
        int less = begin;

        for (int i = begin; i < end; i++) {
            if (toCompare(people.get(i), p)) {
                swap(i, less);
                less++;
            }
        }
        swap(less, end);
        return less;
    }

    /**
     * Рекурсивный метод, сортируем по алгоритму "быстрой сортировки".
     * Переменная position считается опорным элементом, или элемент разделения массивов.
     * @param people - массив элементов
     * @param begin - начало массива
     * @param end - конец массива
     */
    void quickSort(ArrayList<Person> people, int begin, int end) {
        if (begin < end) {
            int position = partition(people, begin, end);
            quickSort(people, begin, position - 1);
            quickSort(people, position + 1, end);
        }
    }

    public void sort() {
        if (!people.isEmpty()) {
            quickSort(people, 0, people.size() - 1);
        }
    }

    private void swap(int i, int j) {
        Person person = this.people.get(j);
        this.people.set(j, this.people.get(i));
        this.people.set(i, person);
//        System.out.println("Смена " + i + " и " + j + " позиции");
//        System.out.println("Смена " + this.people.get(i).toString() + " на " + this.people.get(j).toString());
    }

    public void print() {
        for (Person p: people) {
            System.out.println(p.toString());
        }
    }
}

package part1.lesson03.task01;

import java.util.*;

/**
 * MathBox - объект. Конструктор принимает массив объектов Integer
 *
 * @author Almaz_Kamalov
 */
public class MathBox {

    private ArrayList<Number> numbers = new ArrayList<>();

    public MathBox(Number[] numbers) {
        this.numbers.addAll(Arrays.asList(numbers));
    }

    /**
     * Метод, выводит на экран сумму всех элементов коллекции
     */
    public void summator() {
        int sum = 0;
        for (Number number :
                numbers) {
            sum += number.intValue();
        }
        System.out.println(sum);
    }

    /**
     * Метод, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления
     * @param splitter - делитель
     */
    public void splitter (int splitter) {
        ArrayList<Number> numberSet = new ArrayList<>();
        for (Number number :
                numbers) {
            number = number.doubleValue() / splitter;
            numberSet.add(number);
        }
        numbers = numberSet;
    }

    /**
     * Метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его
     * @param i - элемент удаления из Коллекции
     */
    public void remove(Integer i) {
        Number number = i.doubleValue();
        numbers.remove(number);
    }

    /**
     * Метод, выводящий весь массив на экран
     */
    public void print() {
        for (Number number :
                numbers) {
            System.out.println(number);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return numbers.equals(mathBox.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers.hashCode());
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + numbers +
                '}';
    }
}

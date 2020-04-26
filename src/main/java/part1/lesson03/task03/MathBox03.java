package part1.lesson03.task03;

import java.util.Arrays;
import java.util.Objects;

/**
 * MathBox - объект. Конструктор принимает массив объектов Integer
 *
 * @author Almaz_Kamalov
 */
public class MathBox03 extends ObjectBox03 {

    private final Number[] numbers;

    public MathBox03(Number[] numbers) {
        this.numbers = numbers;
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
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i].intValue() / splitter;
        }
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
        MathBox03 mathBox = (MathBox03) o;
        return numbers.equals(mathBox.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(numbers));
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + Arrays.toString(numbers) +
                '}';
    }
}

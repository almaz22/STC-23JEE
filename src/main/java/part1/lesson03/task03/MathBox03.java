package part1.lesson03.task03;

import java.util.Arrays;
import java.util.Objects;

/**
 * MathBox - объект. Конструктор принимает массив объектов Integer
 *
 * @author Almaz_Kamalov
 */
public class MathBox03 extends ObjectBox03<Number> {

    private final Number[] numbers;

    private final Number[] constructNumber;

    public MathBox03(Number[] numbers) {
        this.numbers = numbers;
        this.constructNumber = new Number[numbers.length];
        System.arraycopy(numbers, 0, this.constructNumber, 0, this.constructNumber.length);
    }

    /**
     * Метод, выводит на экран сумму всех элементов коллекции
     */
    public int summator() {
        int sum = 0;
        for (Number number : numbers) {
            sum += number.intValue();
        }
        return sum;
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
        return Arrays.equals(constructNumber, mathBox.constructNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(constructNumber));
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + Arrays.toString(numbers) +
                '}';
    }
}

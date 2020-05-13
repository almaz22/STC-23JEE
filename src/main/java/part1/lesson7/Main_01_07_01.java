package part1.lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Main_01_07_01
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти,
 * очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого,
 * что будет гораздо быстрее
 *
 * @author Almaz_Kamalov
 */
public class Main_01_07_01 {
    public static void main(String[] args) {
        int arraySize = 10;
        // Создаем массив случайных чисел и оборачиваем в класс FactorialNumber
        FactorialNumber[] factorialNumbers = new FactorialNumber[arraySize];
        for (int i = 0; i < arraySize; i++) {
            factorialNumbers[i] = new FactorialNumber(new Random().nextInt(10));
        }
        // Создаем пул потоков. В качестве потока играет класс CalcFactorialThread
        List<Thread> factorialThreads = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            Thread thread = new CalcFactorialThread(factorialNumbers[i]);
            factorialThreads.add(thread);
        }
        // Запускаем все потоки сразу
        factorialThreads.parallelStream().forEach(Thread::start);
        // Ожидаем, пока все потоки завершаться
        for (Thread thread: factorialThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Вывод результата
        System.out.println(Arrays.toString(factorialNumbers));

    }
}

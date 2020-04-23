package part1.lesson02.task02;

/**
 * Task02 - Программа, генерирующая N случайных чисел. Для каждого числа k из N вычисляется квадратный корень q.
 * Если квадрат целой части q числа равен k, то выводим это число на экран.
 * Предусмотреть, что число k может быть отрицательным, в этом случае генерировать исключение.
 *
 * @author Almaz_Kamalov
 */
public class Task02 {

    public static void main(String[] args){
        try {
            int N = 100;
            for (int i = 0; i < N; i++) {
                double random = Math.random();

                int k = (int) (random * N) * ((random < 0.5) ? -1 : 1);
//                int k = (int) (random * N);

                if (k < 0)
                    throw new RuntimeException("Число отрицательное, нельзя вычислить квадратный корень");

                int q = (int) Math.sqrt(k);

                if ((int) Math.pow(q, 2) == k) {
                    System.out.println("k = " + k);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

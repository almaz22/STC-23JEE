package part1.lesson03.task01;

/**
 * Main_01_03_01 - главный класс
 *
 * @author Almaz_Kamalov
 */
public class Main_01_03_01 {
    public static void main(String[] args) {
        Number[] numbers = new Number[] {100, 10, 50, 20, 25, 115, 22, 43, 27, 17};
        MathBox mathBox = new MathBox(numbers);
        mathBox.summator();
//        mathBox.print();
        mathBox.splitter(5);
        mathBox.remove(2);
        System.out.println(mathBox.toString());
    }
}

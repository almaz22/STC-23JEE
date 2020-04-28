package part1.lesson03.task03;

import part1.lesson03.task01.MathBox;

/**
 * Main_01_03_03
 *
 * @author Almaz_Kamalov
 */
public class Main_01_03_03 {

    public static void main(String[] args) {
        Number[] numbers = new Number[] {100, 10, 50, 20, 25, 115, 22, 43, 27, 17};
        MathBox03 mathBox = new MathBox03(numbers);
        mathBox.addObject(200);
        System.out.println(mathBox.summator());
        mathBox.print();
        mathBox.splitter(5);
        mathBox.deleteObject(2);
        System.out.println(mathBox.toString());
    }

}

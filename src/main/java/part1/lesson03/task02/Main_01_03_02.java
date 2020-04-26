package part1.lesson03.task02;

/**
 * Main_01_03_02
 *
 * @author Almaz_Kamalov
 */
public class Main_01_03_02 {
    public static void main(String[] args) {
        ObjectBox o = new ObjectBox();
        o.addObject(15);
        o.addObject(15);
        o.addObject(20);
        o.addObject(10);

        System.out.println(o.size());
        System.out.println(o.dump());

        o.deleteObject(15);
        System.out.println(o.size());
        System.out.println(o.dump());
    }
}

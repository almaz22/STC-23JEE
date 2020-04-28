package part1.lesson02.task01;

/**
 * HelloWorld
 * Класс для моделирования ошибки «NullPointerException».
 *
 * @author Almaz_Kamalov
 */
public class HelloWorld {

    private String helloWorld;
    public String getHelloWorld() {
        return helloWorld;
    }

    public HelloWorld() {
    }

    public HelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }

    /**
     * Метод предназначен для моделирования ошибки NPE, вызвав Getter с пустым значением
     * и реализацией любого метода класса String. Таким образом мы получим ошибку NPE.
     * @return - возвращаем переменную helloWorld
     */
    String testNPE () {
        return getHelloWorld().toUpperCase();
    }

    public static void main(String[] args)  {
        // Неверная реализация
        HelloWorld helloWorld = new HelloWorld();
        System.out.println(helloWorld.testNPE());

        // Верная реализация
        HelloWorld helloWorld1 = new HelloWorld("Hello, World!");
        System.out.println(helloWorld1.testNPE());
    }

}

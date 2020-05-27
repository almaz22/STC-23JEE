package part1.lesson12;

import part1.lesson9.MyClassLoader;
import part1.lesson9.Worker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GCDemo
 * Для использования Flight Recorder в JMC добавьте флаги:
 *      -XX:+UnlockCommercialFeatures -XX:+FlightRecorder
 *
 * Для моделирования ошибки java.lang.OutOfMemoryError: Java heap space требуется загрузить много различных объектов.
 * @javaHeapOutOfMemoryErrorDemo
 *
 * Для моделирования java.lang.OutOfMemoryError: Metaspace требуется загрузить много различных классов.
 * По умолчанию Java 8 имеет неограниченный максимальный размер для Metaspace,
 * поэтому, до тех пор, пока вы не установите предел с помощью MaxMetaspaceSize флага, ошибка не должна быть вызвана
 * -XX:MaxMetaspaceSize=100m
 * @metaspaceOutOfMemoryDemo
 *
 * Примеры использования различных режимов сборщика мусора:
 *      Пример 1: -XX:+UseSerialGC -Xmx100m -XX:MaxMetaspaceSize=100m
 *      Пример 2: -XX:+UseParallelGC -Xmx100m -XX:MaxMetaspaceSize=100m
 *      Пример 3: -XX:+UseConcMarkSweepGC -Xmx100m -XX:MaxMetaspaceSize=100m
 *      Пример 4: -XX:+UseG1GC -Xmx100m -XX:MaxMetaspaceSize=100m
 *
 * @author Almaz_Kamalov
 */
public class GCDemo {

    private static final int LOOP_COUNT = 100_000_000;

    public static void main(String[] args) {
//        outOfMemoryErrorDemo();
//        metaspaceOutOfMemoryDemo();
        notUnloadedResources();
    }

    static void javaHeapOutOfMemoryErrorDemo()  {
        try {
            List<String> list = new ArrayList<>();

            Random random = new Random();
            for (int i = 0; i < LOOP_COUNT; i++) {
                String str = "" + random.nextInt();
                list.add(str);
                if (i % 10 == 0) {
                    Thread.sleep(1);
                    list.remove(0);
                }
            }
            System.out.println(list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void metaspaceOutOfMemoryDemo ()  {
        try {
            List<Worker> workers = new ArrayList<>();
            for (int i = 0; i < LOOP_COUNT; i++) {
                ClassLoader cl = new MyClassLoader();
                Class<?> someClass = cl.loadClass("part1.lesson9.SomeClass");
                Worker worker = (Worker) someClass.newInstance();
                workers.add(worker);
                if (i % 10 == 0) {
                    Thread.sleep(1);
                    workers.remove(0);
                }
            }
            System.out.println(workers.size());
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
    }

    /**
     * Пример java.lang.OutOfMemoryError: Java heap space с невыгруженными ресурсами (не закрыты BufferedReader)
     */
    static void notUnloadedResources () {
        try {
            List<BufferedReader> list = new ArrayList<>();
            for (int i = 0; i < LOOP_COUNT; i++) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("src/main/resources/lesson8/result.txt"),
                                StandardCharsets.UTF_8));
                list.add(reader);
                if (i % 10 == 0) {
                    Thread.sleep(1);
                    list.remove(0);
                }
            }
            System.out.println(list.size());
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

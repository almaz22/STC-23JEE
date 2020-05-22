package part1.lesson9;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс реализации SomeClass посредством генерации кода класса с интерфейсом Worker и тела метода doWork
 * консольного ввода кода
 */
public class SomeClassGenerator {

    /**
     * Генерация класса
     * @param packageName - название пакета
     * @param className - название класса
     * @param filePath - путь до класса
     * @return - возвращаем true - скомпилирован класс, false - не скомпилирован
     */
    boolean generateSomeClass(String packageName, String className, String filePath) {
        final String fileName = filePath + className + ".java";
        String classBody = createSomeClassBody(packageName, className);
        createSomeClass(classBody, fileName);
        if (compileSomeClass(fileName)) {
            System.out.println("Compile success");
            return true;
        }
        else {
            System.out.println("Compile failed");
            return false;
        }
    }

    /**
     * Генерируем код класса
     * @param packageName - название пакета
     * @param className - название класса
     * @return - возвращаем код класса
     */
    String createSomeClassBody(String packageName, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packageName).append("; \n");
        sb.append("public class ").append(className)
                .append(" implements ").append("Worker")
                .append(" {").append("\n");
        sb.append("\t").append("@Override").append("\n");
        sb.append("\t").append("public void doWork() { ").append("\n");

        // Продолжаем считывать строки с консоли, пока не получим пустую строку
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            if (s.length() == 0)
                break;
            sb.append("\t\t").append(s).append("\n");
        }
        scanner.close();

        sb.append("\t}").append("\n");
        sb.append("}").append("\n");
        return sb.toString();
    }

    /**
     * Генерируем Java класс из входящего кода
     * @param classBody - код класса
     * @param file - навзание  Java класса
     */
    void createSomeClass(String classBody, String file) {
        try (FileOutputStream oos = new FileOutputStream(file)){
            oos.write(classBody.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Компилируем Java класс
     * @param fileName - название класса
     * @return - возвращаем 0 при успешном компилировании класса, любое другое значение - неудача
     */
    boolean compileSomeClass(String fileName) {
        JavaCompiler java = ToolProvider.getSystemJavaCompiler();
        return java.run(null, null, null, fileName) == 0;
    }
}

package part1.lesson6.task1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Main_01_06_01
 * Задание 1. Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 *
 * @author Almaz_Kamalov
 */
public class Main_01_06_01 {

    static String fileName = "src\\main\\resources\\lesson6_task1.txt";
    static String fileNameResult = "src\\main\\resources\\lesson6_task1_result.txt";

    public static void main(String[] args) {
        String text = readFile(fileName);
        HashSet<String> words = stringToHashSet(text);
        ArrayList<String> sortedWords = hashSetToSortArrayList(words);
        text = sortedArrayListToString(sortedWords);
        writeFile(fileNameResult, text);
    }

    static String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    static void writeFile(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileName),
                        StandardCharsets.UTF_8))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HashSet<String> stringToHashSet(String text) {
        text = text.replaceAll("[\\n.,–]|[0-9]", "");
        String[] words = text.toLowerCase().split(" ");
        return new HashSet<>(Arrays.asList(words));
    }

    static ArrayList<String> hashSetToSortArrayList(HashSet<String> words) {
        ArrayList<String> wordsArray = new ArrayList<>(words);
        Comparator<String> sortWords = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        wordsArray.sort(sortWords);
        return wordsArray;
    }

    static String sortedArrayListToString(ArrayList<String> sortedWords) {
        StringBuilder stringBuilder = new StringBuilder();
        sortedWords.remove(null);
        for (String word :
                sortedWords) {
            if (word.equals("")) {
                continue;
            }
            stringBuilder.append(word);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}

package part1.lesson6.task2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 *
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива
 * в следующее предложение (1/probability).
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 * @author Almaz_Kamalov
 */
public class Main_01_06_02 {

    static String path = "src\\main\\resources\\lesson6\\";
    static int countFiles = 5;
    static int probability = 2;

    public static void main(String[] args) {
        getFiles(path, countFiles, 500, createWordsArray(), probability);
    }

    /**
     * Слово состоит из 1<=n2<=15 латинских букв
     * @param capital - заглавная буква
     * @return - возвращает слово
     */
    static String createWord (boolean capital) {
        String letter = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder word = new StringBuilder();
        int wordSize = new Random().nextInt(15) + 1;
        for (int i = 0; i < wordSize; i++) {
            if (capital && i == 0) {
                char c = letter.toUpperCase().charAt(new Random().nextInt(letter.length()));
                word.append(c);
                continue;
            }
            char c = letter.charAt(new Random().nextInt(letter.length()));
            word.append(c);
        }
        return word.toString();
    }

    /**
     * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
     * Предложение начинается с заглавной буквы
     * Предложение заканчивается (.|!|?)+" "
     * @param word - вставляем слово в предложение
     * @return - возвращает преложение
     */
    static String createSentence (String word) {
        String punctualSigns = ".!?";
        char punctual = punctualSigns.charAt(new Random().nextInt(punctualSigns.length()));
        StringBuilder sentence = new StringBuilder();
        int wordCount = new Random().nextInt(15) + 1;
        for (int i = 0; i < wordCount; i++) {
            if (i == 0) {
                sentence.append(createWord(true));
                if (i == wordCount - 1) {
                    sentence.append(punctual);
                } else {
                    if (new Random().nextInt(100) < 25)
                        sentence.append(", ");
                    else
                        sentence.append(" ");
                }
                if (!word.equals("")) {
                    sentence.append(word);
                    sentence.append(" ");
                }
                continue;
            }
            sentence.append(createWord(false));
            if (i == wordCount - 1) {
                sentence.append(punctual).append(" ");
            } else
                if (new Random().nextInt(100) < 25)
                    sentence.append(", ");
                else
                    sentence.append(" ");
        }
        return sentence.toString();
    }

    /**
     * Абзац состоит из 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
     * @param word - вставляем слово в предложение
     * @param probability - вероятность слова
     * @return - возвращает абзац
     */
    static String createParagraph(String word, int probability) {
        StringBuilder paragraph = new StringBuilder();
        int sentenceCount = new Random().nextInt(20) + 1;
        for (int i = 0; i < sentenceCount; i++) {
            if (new Random().nextInt(100) < 1 / probability * 100) {
                paragraph.append(createSentence(word));;
            } else
                paragraph.append(createSentence(""));;
        }
        paragraph.append("\r\n");
        return paragraph.toString();
    }

    /**
     * Есть массив слов 1<=n4<=1000
     * @return - возвращает массив слов
     */
    static String[] createWordsArray() {
        int wordCount = new Random().nextInt(1000) + 1;
        String[] words = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            words[i] = createWord(false);
        }
        return words;
    }

    /**
     * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
     * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
     * Есть вероятность probability вхождения одного из слов массива words в следующее предложение (1/probability).
     * @param path - каталог для файлов
     * @param n - кол-во файлов
     * @param size - размер файлов
     * @param words - массив слов
     * @param probability - вероятность
     */
    static void getFiles(String path, int n, int size, String[] words, int probability) {
        String word = words[new Random().nextInt(words.length)];
        for (int i = 0; i < n; i++) {
            String fileName = path + i + ".txt";
            System.out.println(fileName);
            String text = createParagraph(word, probability);
            System.out.println(text);
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fileName),
                            StandardCharsets.UTF_8))) {
                //writer.write(text);
                writer.write(text, 0, Math.min(text.length(), size));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

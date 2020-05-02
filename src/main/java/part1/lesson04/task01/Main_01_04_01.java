package part1.lesson04.task01;

import part1.lesson02.task03.Person;

import java.util.Random;

import static part1.lesson02.task03.Main.*;
import static part1.lesson02.task03.Person.Sex.*;

/**
 * Main_01_04_01
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке (поиск должен быть эффективным)
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Almaz_Kamalov
 */
public class Main_01_04_01 {

    private static final PetDAOImpl petDAO = new PetDAOImpl();

    public static void main(String[] args) {

        for (int id = 0; id < 10; id++) {
            Pet pet = generatePet(String.valueOf(id));
            petDAO.save(pet);
        }

        System.out.println("Сгенерированный лист питомцев:");
        petDAO.print();
        System.out.println();

        try {
            System.out.println("Обновляем элемент по ID = 1");
            petDAO.updateByPK("1", generatePet("11"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            petDAO.updateByPK("1", generatePet("1"));
        }

        System.out.println();
        System.out.println("Обновленный лист питомцев:");
        petDAO.print();

        System.out.println();

        try {
            System.out.println("Добавляем питомца 'Blansh'");
            Person owner = new Person(25, WOMAN, "Alina");
            Pet pet = new Pet("20", "Blansh", owner, 3);
            petDAO.save(pet);
            System.out.println("Добавим того же питомца повторно");
            petDAO.save(pet);
        } catch (RuntimeException e) {
            System.out.println("Нельзя! " + e.getMessage());
        }

        try {
            System.out.println("Добавляем питомца 'Blansh'");
            Person owner = new Person(30, MAN, "Albert");
            Pet pet = new Pet("20", "Бланш", owner, 5);
            petDAO.save(pet);
            System.out.println("Добавим того жи питомца с другим весом, именем и хозяином");
            pet.setWeight(5);
            pet.setName("Бланш");
            pet.setOwner(generatePerson());
            petDAO.save(pet);
        } catch (RuntimeException e) {
            System.out.println("Нельзя! "+ e.getMessage());
            System.out.println();
            System.out.println("OK, тогда обновим информацию");
            Person owner = new Person(30, MAN, "Albert");
            Pet pet = new Pet("20", "Бланш", owner, 5);
            petDAO.update(pet);
        }

        try {
            System.out.println();
            System.out.println("Поиск питомца по кличке 'Blansh'");
            Pet searchPet = petDAO.findPetByName("Blansh");
            System.out.println(searchPet.toString());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Ах ну да, его теперь записали на кириллице - 'Бланш'");
            Pet searchPet = petDAO.findPetByName("Бланш");
            System.out.println(searchPet.toString());
        }

        System.out.println();
        System.out.println("Отсортированный лист питомцев:");
        petDAO.sortedPrint();

    }

    public static Person generatePerson() {
        Person.Sex sex = randomSex();
        return new Person(randomAge(), sex, randomName(sex));
    }

    public static Pet generatePet(String id) {
        int weight = new Random().nextInt(10);
        Person.Sex sex = weight % 2 == 0 ? MAN : WOMAN;
        return new Pet(id, randomName(sex), generatePerson(), weight);
    }

}

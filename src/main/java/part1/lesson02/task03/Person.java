package part1.lesson02.task03;

/**
 * Person - объект. Содержит поля
 * age - возраст
 * sex - пол
 * name - имя
 * compareTo - метод сравнения по имени, сортируем по алфавиту
 *
 * @author Almaz_Kamalov
 */
public class Person implements Comparable<Person>{
    int age;
    Sex sex;
    String name;

    public Person(int age, Sex sex, String name) {
        if (age < 0 || age > 100)
            throw new RuntimeException("Введите возраст от 0 до 100. Вы ввели: age = " + age);
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public enum Sex {
        MAN(0),
        WOMAN(1);

        int i;

        Sex(int i) {
            this.i = i;
        }

        public int getSex() {
            return i;
        }
    }

    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return  "Пол: " + this.sex + "\tВозраст: " + this.age + "\tИмя: " + this.name;
    }
}

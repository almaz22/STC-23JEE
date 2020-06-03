package part1.lesson04;

import part1.lesson02.task03.Person;

import java.io.Serializable;
import java.util.Objects;

/**
 * Pet
 *
 * @author Almaz_Kamalov
 */
public class Pet implements Identified<String>, Comparable<Pet>, Serializable {
    private final String id;
    private String name;
    private Person owner;
    private int weight;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person getOwner() {
        return owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Pet(String id, String name, Person owner, int weight) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id.equals(pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", кличка ='" + name + '\'' +
                ", хозяин = " + owner.getName() + " возраст: " + owner.getAge() +
                ", вес = " + weight +
                '}';
    }

    @Override
    public int compareTo(Pet o) {
        // Сравниваем по хозяину
        int result = this.owner.compareTo(o.owner);
        // Сортируем по алфавиту
        if (result == 0)
            result = this.name.compareTo(o.name);
        // Сортируем по весу по возрастанию
        if (result == 0)
            result = this.weight - o.weight;

        return result;
    }
}

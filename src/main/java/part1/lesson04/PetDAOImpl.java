package part1.lesson04;

import java.util.*;

/**
 * PetDAOImpl
 *
 * @author Almaz_Kamalov
 */
public class PetDAOImpl extends AbstractDAO <Pet, String> implements PetDAO{
    public PetDAOImpl() {
        super(new HashMap<>());
    }

    public PetDAOImpl(Map<String, Pet> elements) {
        super(elements);
    }

    @Override
    public boolean nameExist(String name) {
        for (Pet pet :
                elements.values()) {
            if (pet.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public Pet findPetByName(String name) {
        for (Pet pet : elements.values()) {
            if (pet.getName().equals(name))
                return pet;
        }
        return null;
    }

    @Override
    public Pet save(Pet ob) {
        if (elements.containsValue(ob)) {
            throw new RuntimeException("Данный объект \n" + ob.toString() + "\nесть в списке");
        }
        return super.save(ob);
    }

    public void print() {
        for (Pet pet :
                elements.values()) {
            System.out.println(pet.toString());
        }
    }

    public void sortedPrint() {
        List<Pet> pets = new ArrayList<>(elements.values());
        Collections.sort(pets);
        for (Pet pet : pets) {
            System.out.println(pet.toString());
        }
    }

}

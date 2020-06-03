package part1.lesson04;

/**
 * PetDAO
 *
 * @author Almaz_Kamalov
 */
public interface PetDAO extends GenericDAO<Pet, String>{
    boolean nameExist(String name);

    Pet findPetByName(String name);
}

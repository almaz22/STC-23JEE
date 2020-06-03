package part1.lesson04;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * AbstractDAO
 *
 * @author Almaz_Kamalov
 */
public class AbstractDAO<T extends Identified<PK>, PK extends Serializable>
        implements GenericDAO<T, PK> {

    protected Map<PK, T> elements;

    public AbstractDAO(Map<PK, T> elements) {
        this.elements = elements;
    }

    @Override
    public T save(T ob) {
        return elements.put(ob.getId(), ob);
    }

    @Override
    public T getByPK(PK key) {
        return elements.get(key);
    }

    @Override
    public void deleteByPK(PK key) {
        elements.remove(key);
    }

    @Override
    public void updateByPK(PK key, T obj) {
        if (!key.equals(obj.getId())) {
            throw new RuntimeException("Неверно указан ID. " +
                    "Предложенный: " + obj.getId() + ". Требуемый: " + key);
        }
        deleteByPK(key);
        save(obj);
    }

    @Override
    public void update(T ob) {
        elements.put(ob.getId(), ob);
    }

    @Override
    public T delete(T ob) {
        return elements.remove(ob.getId());
    }

    @Override
    public Collection<T> getAll() {
        return elements.values();
    }

    @Override
    public Collection<T> addAll(Collection<T> obs) {
        for (T ob :
                obs) {
            elements.put(ob.getId(), ob);
        }
        return obs;
    }
}

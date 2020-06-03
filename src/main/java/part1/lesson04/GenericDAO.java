package part1.lesson04;

import java.io.Serializable;
import java.util.Collection;

/**
 * GenericDAO - интерфейс управления объектами
 *
 * @author Almaz_Kamalov
 */
public interface GenericDAO <T extends Identified <PK>, PK extends Serializable> {
    /** Создает новую запись, соответствующую объекту object */
    T save(T ob);

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    T getByPK(PK key);

    /** Удаляет запись об объекте по первоичном ключу */
    void deleteByPK(PK key);

    /** Сохраняет состояние объекта по первичному ключу */
    void updateByPK(PK key, T obj);

    /** Сохраняет состояние объекта */
    void update(T ob);

    /** Удаляет запись об объекте */
    T delete(T ob);

    /** Возвращает список объектов соответствующих всем записям */
    Collection<T> getAll();

    /** Создает новые записи, соответствующему списку объектов object */
    Collection<T> addAll(Collection<T> obs);

}

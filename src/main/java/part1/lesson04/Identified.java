package part1.lesson04;

import java.io.Serializable;

/**
 * Identified - Интерфейс идентифицируемых объектов
 *
 * @author Almaz_Kamalov
 */
public interface Identified<PK extends Serializable> {
    PK getId();
}

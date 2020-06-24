package part2.lesson22.dao;

import part2.lesson22.pojo.Mobile;
import java.util.List;

public interface MobileDao {

    boolean addMobile(Mobile mobile);

    boolean updateMobile(Integer id, String model, Integer price, String manufacturer);

    boolean deleteMobileById(Integer id);

    Mobile getMobileById(Integer id);

    void createTable();

    List<Mobile> getAllMobile();
}

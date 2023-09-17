package tw.hp.demo06.web.server.repo;

import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.util.List;

public interface IStaffRepository {

    String KEY_PREFIX_STAFF_ITEM = "staff:item:";

    String KEY_STAFF_LIST = "staff:list:";

    //存數據
    void put(StaffDetailVO staffDetailVO);

    //取數據
    StaffDetailVO get(Long id);

    //刪數據
    void deleteItem(Long id);

    //存列表
    void putList(List<StaffListItemVO> list);

    //取列表
    List<StaffListItemVO> getList();

    //刪列表
    void deleteList();

    void update(StaffDetailVO staffDetailVO);

}

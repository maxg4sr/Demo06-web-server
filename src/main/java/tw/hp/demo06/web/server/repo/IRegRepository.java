package tw.hp.demo06.web.server.repo;


import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;

import java.io.Serializable;
import java.util.List;

public interface IRegRepository {

    String KEY_PREFIX_REG_ITEM = "reg:item:";

    String KEY_REG_LIST = "reg:list:";


    //存數據
    void put(RegDetailVO regDetailVO);

    //取數據
    RegDetailVO get(Long id);

    //刪數據
    void deleteItem(Long id);

    //存列表
    void putList(List<RegListItemVO> list);

    //取列表
    List<RegListItemVO> getList();

    //刪列表
    void deleteList();

}

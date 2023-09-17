package tw.hp.demo06.web.server.repo;

import org.springframework.data.redis.core.RedisTemplate;
import tw.hp.demo06.web.server.pojo.vo.OrderDetailVO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.io.Serializable;
import java.util.List;

public interface IOrderRepository {

    String KEY_PREFIX_ORDER_ITEM = "order:item:";

    String KEY_ORDER_LIST = "order:list:";

    void setDateSerializer(RedisTemplate<String, Serializable> redisTemplate);

    //存數據
    void put(OrderDetailVO orderDetailVO);

    //取數據
    OrderDetailVO get(Long id);

    //刪數據
    void deleteItem(Long id);

    //存列表
    void putList(List<OrderListItemVO> list);

    //取列表
    List<OrderListItemVO> getList();

    //刪列表
    void deleteList();

}

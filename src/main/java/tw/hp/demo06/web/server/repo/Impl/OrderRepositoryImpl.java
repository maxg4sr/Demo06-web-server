package tw.hp.demo06.web.server.repo.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.vo.OrderDetailVO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.repo.IOrderRepository;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    public OrderRepositoryImpl() {
        log.debug("員工數據訪問層: {}",OrderRepositoryImpl.class.getSimpleName());
//        System.out.println("員工數據訪問層.staffRepositoryImpl()");
    }

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void setDateSerializer(RedisTemplate<String, Serializable> redisTemplate) {
        // 創建日期序列化器
        Jackson2JsonRedisSerializer<Serializable> serializer = new Jackson2JsonRedisSerializer<>(Serializable.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 註冊日期模塊，支持Java 8日期類型
        serializer.setObjectMapper(objectMapper);

        // 設置值序列化器
        redisTemplate.setValueSerializer(serializer);
    }


    @Override
    public void put(OrderDetailVO orderDetailVO) {
        String key = KEY_PREFIX_ORDER_ITEM + orderDetailVO.getId();
        redisTemplate.opsForValue().set(key, orderDetailVO);
        //opsForValue() 是Spring Framework中用於與Redis進行操作的一個方法，通常用於操作Redis的String類型數據（key-value對）
    }

    @Override
    public OrderDetailVO get(Long id) {
        String key = KEY_ORDER_LIST + id;
        return (OrderDetailVO) redisTemplate.opsForValue().get(key);
    }
//        OrderDetailVO order = null;
//        Serializable serializable = redisTemplate.opsForValue().get(key);
//        if (serializable != null) {
//            if (serializable instanceof OrderDetailVO) {
//                order = (OrderDetailVO) serializable;
//            }
//        }
//        return order;
//    }

    @Override
    public void deleteItem(Long id) {
        String key = KEY_PREFIX_ORDER_ITEM + id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<OrderListItemVO> list) {
        log.debug("向redis中寫入列表數據:{}", list);
        for (OrderListItemVO orderListItemVO : list) {
            redisTemplate.opsForList().rightPush(KEY_ORDER_LIST, orderListItemVO);
        }
    }

    @Override
    public List<OrderListItemVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_ORDER_LIST, 0, -1);
        LinkedList<OrderListItemVO> order = new LinkedList<>();
        for (Serializable serializable : list) {
            order.add((OrderListItemVO) serializable);
        }
        log.debug("向redis中讀取列表數據:{}", list);
        return order;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_ORDER_LIST);
    }
}

package tw.hp.demo06.web.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.hp.demo06.web.server.mapper.OrderMapper;
import tw.hp.demo06.web.server.mapper.StaffMapper;
import tw.hp.demo06.web.server.pojo.entity.OrderEntity;
import tw.hp.demo06.web.server.pojo.entity.StaffEntity;
import tw.hp.demo06.web.server.pojo.vo.OrderDetailVO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderMapperTests {

    @Autowired
    OrderMapper orderMapper;
    
    @Test
    public void testInsert() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("7788米2");
        orderEntity.setAge(78);
        int rows = orderMapper.insert(orderEntity);
        System.out.println("受影響的行數:" + rows);
        System.out.println("id:" + orderEntity.getId());
        System.out.println("age:" + orderEntity.getAge());
    }

    @Test
    public void testDeleteById() {
        Long id = 20L;
        int rows = orderMapper.deleteById(id);
        System.out.println("刪除的行數:" + rows);
    }

    @Test
    public void testDeleteByIds() {
        int ids = orderMapper.deleteByIds(2L, 3L);
        System.out.println("批量已刪除的行數:" + ids);
    }

    @Test
    public void testUpdateById() {
        Long id = 2L;
        String name = "78米";
        String phone = "0980-000-888";
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setName(name);
        orderEntity.setPhone(phone);


        int byId = orderMapper.updateById(orderEntity);
        System.out.println("品牌修改完成,受影響的行數" + byId);

    }


    @Test
    public void testUpdateNameById() {
        Long id = 2L;
        String name = "黑米蔡";
        int rows = orderMapper.updateNameById(id, name);
        System.out.println("修改行數:" + rows);

    }


    @Test
    public void testCount() {
        int count = orderMapper.count();
        System.out.println("統計訂單數量完畢:" + count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        OrderDetailVO byId = orderMapper.getById(id);
        System.out.println("品牌查詢完成:" + byId);
    }

    @Test
    public void testList() {
        List<OrderListItemVO> list = orderMapper.list();
        for (OrderListItemVO order : list) {
            System.out.println(order);
        }
    }

}


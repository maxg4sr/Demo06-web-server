package tw.hp.demo06.web.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.hp.demo06.web.server.mapper.StaffMapper;
import tw.hp.demo06.web.server.pojo.entity.StaffEntity;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.util.List;

@SpringBootTest
public class StaffMapperTests {
    @Autowired
    StaffMapper staffMapper;
    
    @Test
    public void testInsert() {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName("7788米");
        staffEntity.setNickname("196mi");
        int rows = staffMapper.insert(staffEntity);
        System.out.println("受影響的行數:" + rows);
        System.out.println("id:" + staffEntity.getId());
        System.out.println("sort:" + staffEntity.getSort());
    }

    @Test
    public void testDeleteById() {
        Long id = 20L;
        int rows = staffMapper.deleteById(id);
        System.out.println("刪除的行數:" + rows);
    }

    @Test
    public void testDeleteByIds() {
        int ids = staffMapper.deleteByIds(2L, 3L);
        System.out.println("批量已刪除的行數:" + ids);
    }

    @Test
    public void testUpdateById() {
        Long id = 1L;
        String name = "78米";
        String avatar = "1.jpg";
        StaffEntity StaffEntity = new StaffEntity();
        StaffEntity.setId(id);
        StaffEntity.setName(name);
        StaffEntity.setAvatar(StaffEntity.getAvatar());

        int byId = staffMapper.updateById(StaffEntity);
        System.out.println("品牌修改完成,受影響的行數" + byId);

    }


    @Test
    public void testUpdateNameById() {
        Long id = 6L;
        String name = "黑米蔡";
        int rows = staffMapper.updateNameById(id, name);
        System.out.println("修改行數:" + rows);

    }


    @Test
    public void testCount() {
        int count = staffMapper.count();
        System.out.println("統計品牌數量完畢:" + count);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        StaffDetailVO byId = staffMapper.getById(id);
        System.out.println("品牌查詢完成:" + byId);
    }

    @Test
    public void testList() {
        List<StaffListItemVO> list = staffMapper.list();
        for (StaffListItemVO staff : list) {
            System.out.println(staff);
        }
    }



}


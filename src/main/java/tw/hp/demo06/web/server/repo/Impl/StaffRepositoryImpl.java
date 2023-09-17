package tw.hp.demo06.web.server.repo.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;
import tw.hp.demo06.web.server.repo.IStaffRepository;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class StaffRepositoryImpl implements IStaffRepository {

    public StaffRepositoryImpl() {
        System.out.println("員工數據訪問層.staffRepositoryImpl()");
    }

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void put(StaffDetailVO staffDetailVO) {
        String key = KEY_PREFIX_STAFF_ITEM + staffDetailVO.getId();
        redisTemplate.opsForValue().set(key, staffDetailVO);
        //opsForValue() 是Spring Framework中用於與Redis進行操作的一個方法，通常用於操作Redis的String類型數據（key-value對）
    }

    @Override
    public StaffDetailVO get(Long id) {
        String key = KEY_STAFF_LIST + id;
        StaffDetailVO staff = null;
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable != null) {
            if (serializable instanceof StaffDetailVO) {
                staff = (StaffDetailVO) serializable;
            }
        }
        return staff;
    }

    @Override
    public void deleteItem(Long id) {
        String key = KEY_PREFIX_STAFF_ITEM + id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<StaffListItemVO> list) {
        log.debug("向redis中寫入列表數據:{}", list);
        for (StaffListItemVO staffListItemVO : list) {
            redisTemplate.opsForList().rightPush(KEY_STAFF_LIST, staffListItemVO);
        }
    }

    @Override
    public List<StaffListItemVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_STAFF_LIST, 0, -1);
        LinkedList<StaffListItemVO> staff = new LinkedList<>();
        for (Serializable serializable : list) {
            staff.add((StaffListItemVO) serializable);
        }
        log.debug("向redis中讀取列表數據:{}", list);
        return staff;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_STAFF_LIST);
    }

    @Override
    public void update(StaffDetailVO staffDetailVO) {
        Long id = staffDetailVO.getId();
        String key = KEY_PREFIX_STAFF_ITEM + id;

        // 查找現有的員工信息
        StaffDetailVO existingStaff = (StaffDetailVO) redisTemplate.opsForValue().get(key);

        if (existingStaff != null) {
            // 更新相關的字段
            existingStaff.setName(staffDetailVO.getName());
            existingStaff.setNickname(staffDetailVO.getNickname());
            // 其他字段也需要相應的更新

            // 保存更新後的員工信息
            redisTemplate.opsForValue().set(key, existingStaff);
        } else {
            // 如果找不到員工信息，可以選擇拋出異常或執行其他處理邏輯
            // 這取決於您的應用程序需求
            log.error("找不到要更新的員工信息，ID：" + id);
        }
    }
}

package tw.hp.demo06.web.server.repo.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.repo.IRegRepository;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class RegRepositoryImpl implements IRegRepository {

    public RegRepositoryImpl() {
        System.out.println("註冊數據訪問層.RegRepositoryImpl()");
    }

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    @Override
    public void put(RegDetailVO regDetailVO) {
        String key = KEY_PREFIX_REG_ITEM + regDetailVO.getName();
        redisTemplate.opsForValue().set(key, regDetailVO);
    }

    @Override
    public RegDetailVO get(Long id) {
        String key = KEY_REG_LIST + id;
        RegDetailVO reg = null;
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable != null) {
            if (serializable instanceof RegDetailVO) {
                reg = (RegDetailVO) serializable;
            }
        }
        return reg;
    }

    @Override
    public void deleteItem(Long id) {
        String key = KEY_PREFIX_REG_ITEM + id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<RegListItemVO> list) {
        log.debug("向redis中寫入列表數據:{}", list);
        for (RegListItemVO regListItemVO : list) {
            redisTemplate.opsForList().rightPush(KEY_REG_LIST, regListItemVO);
        }
    }

    @Override
    public List<RegListItemVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_REG_LIST, 0, -1);
        LinkedList<RegListItemVO> reg = new LinkedList<>();
        for (Serializable serializable : list) {
            reg.add((RegListItemVO) serializable);
        }
        log.debug("向redis中讀取列表數據:{}", list);
        return reg;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_REG_LIST);
    }
}

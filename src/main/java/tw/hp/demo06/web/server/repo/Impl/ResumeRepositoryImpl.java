package tw.hp.demo06.web.server.repo.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeDetailVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeListItemVO;
import tw.hp.demo06.web.server.repo.IRegRepository;
import tw.hp.demo06.web.server.repo.IResumeRepository;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class ResumeRepositoryImpl implements IResumeRepository {

    public ResumeRepositoryImpl(){
        log.debug("履歷數據訪問層.ResumeRepositoryImpl()");
    }

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void put(ResumeDetailVO resumeDetailVO) {
        String key = KEY_PREFIX_RESUME_ITEM + resumeDetailVO.getChineseName();
        redisTemplate.opsForValue().set(key,resumeDetailVO);
    }

    @Override
    public ResumeDetailVO get(Long id) {
        String key = KEY_RESUME_LIST + id;
        ResumeDetailVO resume = null;
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable != null) {
            if (serializable instanceof ResumeDetailVO) {
                resume = (ResumeDetailVO) serializable;
            }
        }
        return resume;
    }

    @Override
    public void deleteItem(Long id) {
        String key = KEY_PREFIX_RESUME_ITEM + id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<ResumeListItemVO> list) {
        log.debug("向redis中寫入列表數據:{}", list);
        for (ResumeListItemVO resumeListItemVO : list) {
            redisTemplate.opsForList().rightPush(KEY_RESUME_LIST, resumeListItemVO);
        }
    }

    @Override
    public List<ResumeListItemVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_RESUME_LIST, 0, -1);
        LinkedList<ResumeListItemVO> resume = new LinkedList<>();
        for (Serializable serializable : list) {
            resume.add((ResumeListItemVO) serializable);
        }
        log.debug("向redis中讀取列表數據:{}", list);
        return resume;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_RESUME_LIST);
    }

    @Override
    public void update(ResumeDetailVO resumeDetailVO) {

    }
}

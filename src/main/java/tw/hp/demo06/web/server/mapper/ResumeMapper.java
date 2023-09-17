package tw.hp.demo06.web.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.entity.RegEntity;
import tw.hp.demo06.web.server.pojo.entity.ResumeEntity;
import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeDetailVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeListItemVO;

import java.util.List;

@Repository
public interface ResumeMapper {
    int insert(ResumeEntity resumeEntity);


    int deleteById(Long id);


    int deleteByIds(Long... ids);//可變長度參數列表[數組]


    int updateNameById(@Param("id") Long id, @Param("name") String name);


    int updateById(ResumeEntity resumeEntity);


    int count();

    ResumeDetailVO getById(Long id);


    int countByName(String name);

    List<ResumeListItemVO> list();
}

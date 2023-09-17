package tw.hp.demo06.web.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.entity.OrderEntity;
import tw.hp.demo06.web.server.pojo.entity.RegEntity;
import tw.hp.demo06.web.server.pojo.vo.OrderDetailVO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;

import java.util.List;

@Repository
public interface RegMapper {

    int insert(RegEntity regEntity);


    int deleteById(Long id);


    int deleteByIds(Long... ids);//可變長度參數列表[數組]


    int updateNameById(@Param("id") Long id, @Param("name") String name);


    int updateById(RegEntity regEntity);


    int count();

    RegDetailVO getById(Long id);


    int countByName(String name);

    List<RegListItemVO> list();

}
package tw.hp.demo06.web.server.service;

import tw.hp.demo06.web.server.pojo.dto.OrderAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.RegAddNewDTO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;

import java.util.List;

public interface IRegService {


    void addNew(RegAddNewDTO regAddNewDTO);


    void deleteById(Long id);


    List<RegListItemVO> list();

}


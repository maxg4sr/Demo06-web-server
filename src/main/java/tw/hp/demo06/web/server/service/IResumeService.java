package tw.hp.demo06.web.server.service;

import tw.hp.demo06.web.server.pojo.dto.RegAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.ResumeAddNewDTO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeListItemVO;

import java.util.List;

public interface IResumeService {

    void addNew(ResumeAddNewDTO resumeAddNewDTO);


    void deleteById(Long id);


    List<ResumeListItemVO> list();
}

package tw.hp.demo06.web.server.repo;

import tw.hp.demo06.web.server.pojo.vo.ResumeDetailVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeListItemVO;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.util.List;

public interface IResumeRepository {

    String KEY_PREFIX_RESUME_ITEM = "resume:item:";

    String KEY_RESUME_LIST = "resume:list:";

    //存數據
    void put(ResumeDetailVO resumeDetailVO);

    //取數據
    ResumeDetailVO get(Long id);

    //刪數據
    void deleteItem(Long id);

    //存列表
    void putList(List<ResumeListItemVO> list);

    //取列表
    List<ResumeListItemVO> getList();

    //刪列表
    void deleteList();

    void update(ResumeDetailVO resumeDetailVO);

}

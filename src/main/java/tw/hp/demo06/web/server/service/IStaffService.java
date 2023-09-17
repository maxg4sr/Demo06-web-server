package tw.hp.demo06.web.server.service;

import tw.hp.demo06.web.server.pojo.dto.StaffAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.StaffEditDTO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.util.List;

public interface IStaffService {

    /**
     * 添加員工
     *
     * @param staffAddNewDTO 需要添加員工的訊息
     */
    void addNew(StaffAddNewDTO staffAddNewDTO);

    /**
     * 根據員工id刪除添加員工
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查詢添加員工列表
     *
     * @return 添加員工列表
     */
    List<StaffListItemVO> list();

    /**
     * 修改員工信息
     *
     * @param staffId       員工的ID
     * @param staffEditDTO  包含要更新的員工信息的DTO
     */
    void updateStaff(Long staffId, StaffEditDTO staffEditDTO);

}

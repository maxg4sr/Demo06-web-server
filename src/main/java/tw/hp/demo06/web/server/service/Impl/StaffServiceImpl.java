package tw.hp.demo06.web.server.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.hp.demo06.web.server.ex.ServiceException;
import tw.hp.demo06.web.server.mapper.StaffMapper;
import tw.hp.demo06.web.server.pojo.dto.StaffAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.StaffEditDTO;
import tw.hp.demo06.web.server.pojo.entity.StaffEntity;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;
import tw.hp.demo06.web.server.repo.IStaffRepository;
import tw.hp.demo06.web.server.service.IStaffService;
import web.ServiceCode;

import java.util.List;

@Slf4j
@Service
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private StaffMapper staffMapper;

    public StaffServiceImpl() {
        log.debug("品牌業務層.StaffServiceImpl()");
    }

    @Override
    @Transactional
    public void addNew(StaffAddNewDTO staffAddNewDTO) throws ServiceException {
        //檢查名稱是否在數據庫裡面被使用,如果被使用就不允許創建
        int count = staffMapper.countByName(staffAddNewDTO.getName());
        if (count > 0) {
            String message = "添加失敗,品牌名稱【" + staffAddNewDTO.getName() + "】已經被使用,請更換名稱";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //創建實體對象staff
        StaffEntity staff = new StaffEntity();
        //將當前方法的參數屬性賦值到實體類的屬性值中
        BeanUtils.copyProperties(staffAddNewDTO, staff);
        //補全屬性值
        staff.setId(null); // 如果需要，設定id為null或其他默認值
        staff.setName(staffAddNewDTO.getName());
        staff.setNickname(staffAddNewDTO.getNickname());
        staff.setPhone(staffAddNewDTO.getPhone());
        staff.setEmail(staffAddNewDTO.getEmail());
        staff.setAvatar(staffAddNewDTO.getAvatar());
        staff.setDescription(staffAddNewDTO.getDescription());
        staff.setSalary(staffAddNewDTO.getSalary());
        staff.setEnable(0);
        staff.setSort(0); // 設定其他默認值

        //調用數據訪問層方法
        int rows = staffMapper.insert(staff);
        if (rows != 1) {
            String message = "添加數據失敗,服務器繁忙,請稍後重試!";
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("開始刪除品牌業務，id={}", id);

        // 根據id查詢數據
        StaffDetailVO staff = staffMapper.getById(id);
        if (staff == null) {
            String message = "刪除數據失敗，刪除的數據(id=" + id + ")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 調用mapper層方法
        int rows = staffMapper.deleteById(id);
        if (rows != 1) {
            String message = "刪除數據失敗，服務器繁忙，請稍後重試";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    @Transactional
    public List<StaffListItemVO> list() {
        log.debug("開始處理查詢品列表的業務");

        // 1.嘗試從Redis中獲取數據
        List<StaffListItemVO> list = staffRepository.getList();
        // 2.如果Redis中沒有有效的數據(集合為空)，則嘗試從MySQL中獲取數據
        if (list.size() == 0) {
        // 3.如果MySQL中獲取到數據，則寫入到Redis中
        List<StaffListItemVO> dbList = staffMapper.list();
        if (dbList.size() > 0) {
        staffRepository.putList(dbList);
        return dbList;}
        }
        // 4.如果MySQL中沒有獲取到數據(集合為空),則放棄向Redis中寫入數據
        // 5.返回Redis的查詢結果(MySQL中有有效的數據,且寫入到Redis中數據)
        return list;
    }

    @Override
    @Transactional
    public void updateStaff(Long staffId, StaffEditDTO staffEditDTO) {
        if (staffId==null){
            log.debug("無法修改成功: {}",staffId);
            log.debug("沒有調用到:{}",staffEditDTO);
        }
    }
}

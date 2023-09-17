package tw.hp.demo06.web.server.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.hp.demo06.web.server.ex.ServiceException;
import tw.hp.demo06.web.server.mapper.ResumeMapper;
import tw.hp.demo06.web.server.mapper.StaffMapper;
import tw.hp.demo06.web.server.pojo.dto.ResumeAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.StaffAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.StaffEditDTO;
import tw.hp.demo06.web.server.pojo.entity.ResumeEntity;
import tw.hp.demo06.web.server.pojo.entity.StaffEntity;
import tw.hp.demo06.web.server.pojo.vo.ResumeDetailVO;
import tw.hp.demo06.web.server.pojo.vo.ResumeListItemVO;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;
import tw.hp.demo06.web.server.repo.IResumeRepository;
import tw.hp.demo06.web.server.repo.IStaffRepository;
import tw.hp.demo06.web.server.service.IResumeService;
import web.ServiceCode;

import java.util.List;

@Slf4j
@Service
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    private IResumeRepository resumeRepository;

    @Autowired
    private ResumeMapper resumeMapper;

    public ResumeServiceImpl() {
        log.debug("履歷業務層.ResumeServiceImpl()");
    }

    @Override
    @Transactional
    public void addNew(ResumeAddNewDTO resumeAddNewDTO) throws ServiceException {
        //檢查名稱是否在數據庫裡面被使用,如果被使用就不允許創建
        int count = resumeMapper.countByName(resumeAddNewDTO.getChineseName());
        if (count > 0) {
            String message = "添加失敗,品牌名稱【" + resumeAddNewDTO.getChineseName() + "】已經被使用,請更換名稱";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //創建實體對象staff
        ResumeEntity resume = new ResumeEntity();
        //將當前方法的參數屬性賦值到實體類的屬性值中
        BeanUtils.copyProperties(resumeAddNewDTO, resume);
        //補全屬性值
        resume.setId(null); // 如果需要，設定id為null或其他默認值
        resume.setChineseName(resumeAddNewDTO.getChineseName());
        resume.setEnglishName(resumeAddNewDTO.getEnglishName());
        resume.setBirthDate(resumeAddNewDTO.getBirthDate());
        resume.setCurrentAddress(resumeAddNewDTO.getCurrentAddress());
        resume.setPhoneNumber(resumeAddNewDTO.getPhoneNumber());
        resume.setEmail(resumeAddNewDTO.getEmail());
//        resume.setResume(resumeAddNewDTO.getResume());

        //調用數據訪問層方法
        int rows = resumeMapper.insert(resume);
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
        ResumeDetailVO resume = resumeMapper.getById(id);
        if (resume == null) {
            String message = "刪除數據失敗，刪除的數據(id=" + id + ")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 調用mapper層方法
        int rows = resumeMapper.deleteById(id);
        if (rows != 1) {
            String message = "刪除數據失敗，服務器繁忙，請稍後重試";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    @Transactional
    public List<ResumeListItemVO> list() {
        log.debug("開始處理查詢品列表的業務");

        // 1.嘗試從Redis中獲取數據
        List<ResumeListItemVO> list = resumeRepository.getList();
        // 2.如果Redis中沒有有效的數據(集合為空)，則嘗試從MySQL中獲取數據
        if (list.size() == 0) {
            // 3.如果MySQL中獲取到數據，則寫入到Redis中
            List<ResumeListItemVO> dbList = resumeMapper.list();
            if (dbList.size() > 0) {
                resumeRepository.putList(dbList);
                return dbList;}
        }
        // 4.如果MySQL中沒有獲取到數據(集合為空),則放棄向Redis中寫入數據
        // 5.返回Redis的查詢結果(MySQL中有有效的數據,且寫入到Redis中數據)
        return list;
    }


}


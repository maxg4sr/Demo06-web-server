package tw.hp.demo06.web.server.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.hp.demo06.web.server.ex.ServiceException;
import tw.hp.demo06.web.server.mapper.OrderMapper;
import tw.hp.demo06.web.server.mapper.RegMapper;
import tw.hp.demo06.web.server.pojo.dto.OrderAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.RegAddNewDTO;
import tw.hp.demo06.web.server.pojo.entity.OrderEntity;
import tw.hp.demo06.web.server.pojo.entity.RegEntity;
import tw.hp.demo06.web.server.pojo.vo.OrderDetailVO;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.RegDetailVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.repo.IOrderRepository;
import tw.hp.demo06.web.server.repo.IRegRepository;
import tw.hp.demo06.web.server.service.IOrderService;
import tw.hp.demo06.web.server.service.IRegService;
import web.ServiceCode;

import java.util.List;

@Slf4j
@Service
public class RegServiceImpl implements IRegService {

    @Autowired
    public IRegRepository regRepository;

    @Autowired
    private RegMapper regMapper;

    @Autowired
    public RegServiceImpl() {
        log.debug("創建業務邏輯對象：RegServiceImpl()");
    }


    @Override
    @Transactional
    public void addNew(RegAddNewDTO regAddNewDTO) throws ServiceException {
        //檢查名稱是否在數據庫裡面被使用,如果被使用就不允許創建
        int count = regMapper.countByName(regAddNewDTO.getName());
        if (count > 0) {

            String message = "添加失敗,訂單名稱【" + regAddNewDTO.getName() + "】已經被使用,請更換名稱";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);

        }

        // 創建實體對象 Order
        RegEntity order = new RegEntity();
        // 將當前方法的參數屬性賦值到實體類的屬性值中
        BeanUtils.copyProperties(regAddNewDTO, order);


        //調用數據訪問層方法
        int rows = regMapper.insert(order);
        if (rows != 1) {
            String message = "添加數據失敗,服務器繁忙,請稍後重試!";
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("開始刪除訂單業務，id={}", id);

        // 根據id查詢數據
        RegDetailVO reg = regMapper.getById(id);
        if (reg == null) {
            String message = "刪除數據失敗，刪除的數據(id=" + id + ")不存在";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 調用mapper層方法
        int rows = regMapper.deleteById(id);
        if (rows != 1) {
            String message = "刪除數據失敗，服務器繁忙，請稍後重試";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    @Transactional
    public List<RegListItemVO> list() {
        log.debug("開始處理查詢品列表的業務");

        // 1.嘗試從Redis中獲取數據
        List<RegListItemVO> list = regRepository.getList();
        // 2.如果Redis中沒有有效的數據(集合為空)，則嘗試從MySQL中獲取數據
        if (list.size() == 0) {
            // 3.如果MySQL中獲取到數據，則寫入到Redis中
            List<RegListItemVO> dbList = regMapper.list();
            if (dbList.size() > 0) {
                regRepository.putList(dbList);
                return dbList;
            }
        }
        // 4.如果MySQL中沒有獲取到數據(集合為空),則放棄向Redis中寫入數據
        // 5.返回Redis的查詢結果(MySQL中有有效的數據,且寫入到Redis中數據)
        log.debug("0001最終結果:{}", list);
        return list;

    }


}

package tw.hp.demo06.web.server.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.hp.demo06.web.server.mapper.OrderMapper;
import tw.hp.demo06.web.server.mapper.RegMapper;
import tw.hp.demo06.web.server.mapper.StaffMapper;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;
import tw.hp.demo06.web.server.repo.IOrderRepository;
import tw.hp.demo06.web.server.repo.IRegRepository;
import tw.hp.demo06.web.server.repo.IStaffRepository;

import java.util.List;

/**
 * 定期更新組件類
 */
@Slf4j
@Component
public class CacheSchedule {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RegMapper regMapper;

    @Autowired
    private IStaffRepository staffRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IRegRepository regRepository;


    public CacheSchedule() {
        log.debug("創建計劃任務對象:CacheSchedule");
    }

    //    @Scheduled(cron = "20 17 16 ? * THU")//時間註解 執行這個功能
    public void updateCache() {
        log.debug("執行計劃任務，更新緩存中的品牌列表...");

        // 將Redis中的品牌列表刪除
        staffRepository.deleteList();
        orderRepository.deleteList();
        regRepository.deleteList();
        // 從MySQL讀取品牌列表
        List<StaffListItemVO> list1 = staffMapper.list();
        List<OrderListItemVO> list2 = orderMapper.list();
        List<RegListItemVO> list3 = regMapper.list();


        // 將品牌列表寫入到Redis
        staffRepository.putList(list1);
        orderRepository.putList(list2);
        regRepository.putList(list3);
    }

    // 保留原有的定時任務設定，但實際執行時調用公共的updateCache方法
    @Scheduled(cron = "20 17 16 ? * THU")
    public void scheduledUpdateCache() {
        updateCache();
    }

}


// fixedRate = 1 * 60 * 60 * 1000 // 固定間隔，毫秒級的時間上可能略有差異
// fixedDelay = 1 * 60 * 60 * 1000 // 距離上次執行之後多久再執行下一次計劃任務
// cron - 每個月的周一(不論幾號)的19:41:20執行此任務
//                 秒 分 時 日 月 週
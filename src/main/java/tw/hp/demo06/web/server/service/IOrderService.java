package tw.hp.demo06.web.server.service;

import tw.hp.demo06.web.server.pojo.dto.OrderAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.OrderEditDTO;
import tw.hp.demo06.web.server.pojo.entity.OrderEntity;
import tw.hp.demo06.web.server.pojo.vo.OrderListItemVO;


import java.util.List;

public interface IOrderService {

    /**
     * 添加訂單
     *
     * @param orderAddNewDTO 需要添加訂單的訊息
     */
    void addNew(OrderAddNewDTO orderAddNewDTO);

    /**
     * 根據訂單id刪除添加訂單
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查詢添加訂單列表
     *
     * @return 添加訂單列表
     */
    List<OrderListItemVO> list();

}


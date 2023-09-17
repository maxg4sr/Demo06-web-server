package tw.hp.demo06.web.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tw.hp.demo06.web.server.pojo.entity.StaffEntity;
import tw.hp.demo06.web.server.pojo.vo.StaffDetailVO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;

import java.util.List;

//mapper層抽象方法的定義
@Repository
public interface StaffMapper {

    /**
     * 插入員工數據抽象方法定義
     *
     * @param staffEntity 員工數據
     * @return 受影響的行數 成功插入數據時 返回1
     */
    int insert(StaffEntity staffEntity);

    /**
     * 根據id刪除某個品牌
     *
     * @param id 期望刪除的品牌數據id
     * @return 受影響的行數 成功刪除數據時 返回1 , 如果沒有此id 則返回0
     */
    int deleteById(Long id);

    /**
     * 根據若干id 一次性刪除多個品牌
     *
     * @param ids 若干品牌id
     * @return
     */
    int deleteByIds(Long... ids);//可變長度參數列表[數組]

    /**
     * 根據id修改 pms_brand 表鍾某調數據的 name 字段值
     *
     * @param id   數據id
     * @param name 品牌名稱
     * @return 受影響的行數 成功修改數據時 返回1 如果無此id 則返回0
     */
    //@Param(參數的意思) 這個註解可以標示參數名,
    //就不用怕使用其他的腳手架報錯,如果只有一個參數就不需要
    int updateNameById(@Param("id") Long id, @Param("name") String name);

    /**
     * 使用1個方法 實現多種不同的數據更新
     * (想更新哪裡就更新哪裡的字段) 不想更新則保持不變
     *
     * @param staffEntity 不同數據
     * @return 受影響的行數 成功修改數據時 返回1 , 如果沒有此id 則返回0
     */
    int updateById(StaffEntity staffEntity);

    /**
     * 統計品牌表中的數據數量是多少
     *
     * @return
     */
    int count();

    /**
     * 根據id查詢品牌詳情
     *
     * @param id 品牌id
     * @return 品牌詳情
     */
    StaffDetailVO getById(Long id);

    /**
     * 根據品牌名稱統計品牌數據的數量
     *
     * @param name
     * @return
     */
    int countByName(String name);

    /**
     * 查詢品牌列表
     *
     * @return 品牌列表
     */
    List<StaffListItemVO> list();


}

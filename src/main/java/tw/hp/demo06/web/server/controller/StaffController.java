package tw.hp.demo06.web.server.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.hp.demo06.web.server.pojo.dto.StaffAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.StaffEditDTO;
import tw.hp.demo06.web.server.pojo.vo.StaffListItemVO;
import tw.hp.demo06.web.server.service.IStaffService;
import web.JsonResult;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "1.員工管理模塊")//一級菜單中所顯示的標題名稱
@Slf4j//使用這個註解可以把打印變成日誌，還有等級可以改為log.(等級)
@RestController //ResponseBody + Controller 開啟回應本文(響應正文)模式
@RequestMapping("/staffs")//拼接前墜
public class StaffController {

    @Autowired
    private IStaffService staffService;

    public StaffController() {
        log.debug("員工控制器層.StaffController()");
    }

    @ApiOperation("添加員工")//業務名稱
    @ApiOperationSupport(order = 100)//幫模塊進行排序
    @PostMapping("/add-new")//此註解主要作用是 請情路徑 與 處理請求方法的 映射關係
    public JsonResult addNew(@RequestBody @Valid StaffAddNewDTO staffAddNewDTO) {
        log.debug("StaffController.addNew()");
        log.debug("員工名稱：{},員工暱稱：{}", staffAddNewDTO.getName(), staffAddNewDTO.getNickname());

        // 調用業務層
        staffService.addNew(staffAddNewDTO);

        return JsonResult.ok();
    }

    @ApiOperation("刪除員工")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id}/delete")//此註解主要作用是 請情路徑 與 處理請求方法的 映射關係
    public JsonResult delete(@PathVariable Long id) {//需要增加long類型的id
        log.debug("StaffController.delete()");
        log.debug("根據id=" + id + "刪除類別");

        staffService.deleteById(id);

        return JsonResult.ok();
    }

    // 使用@PathVariable的註解體現 {}佔位符字節
    @ApiOperation("修改員工")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult edit(@PathVariable("id") Long staffId, StaffEditDTO staffEditDTO) {
        log.debug("StaffController.edit()");
        log.debug("根據數字id=" + staffId + "修改類別");
        // 調用Service層方法執行更新
        staffService.updateStaff(staffId, staffEditDTO);
        return JsonResult.ok();
    }

    @ApiOperation("查詢員工列表")
    @ApiOperationSupport(order = 400)
    @GetMapping//RESTful風格
    public JsonResult list() {
        log.debug("StaffController.list()");

        // 調用業務層方法
        List<StaffListItemVO> list = staffService.list();
        return JsonResult.ok(list);
    }


}

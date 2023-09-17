package tw.hp.demo06.web.server.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import tw.hp.demo06.web.server.pojo.dto.RegAddNewDTO;
import tw.hp.demo06.web.server.pojo.dto.RegEditDTO;
import tw.hp.demo06.web.server.pojo.vo.RegListItemVO;
import tw.hp.demo06.web.server.service.IRegService;
import web.JsonResult;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "3.註冊管理模塊")//一級菜單中所顯示的標題名稱
@Slf4j//使用這個註解可以把打印變成日誌，還有等級可以改為log.(等級)
@RestController //ResponseBody + Controller 開啟回應本文(響應正文)模式
@RequestMapping("/regs")//拼接前墜
public class RegController {

    @Autowired
    @Qualifier("regServiceImpl")
    private IRegService regService;

    public RegController() {
        log.debug("註冊控制器層.RegController()");
    }

    @ApiOperation("添加註冊名單")//業務名稱
    @ApiOperationSupport(order = 100)//幫模塊進行排序
    @PostMapping("/add-new")//此註解主要作用是 請情路徑 與 處理請求方法的 映射關係
    public JsonResult addNew(@RequestBody @Valid RegAddNewDTO regAddNewDTO) {
        log.debug("RegController.addNew()");
        log.debug("註冊名稱：{},定單日期：{}", regAddNewDTO.getName(), regAddNewDTO.getDate());


        // 調用業務層
        regService.addNew(regAddNewDTO);

        return JsonResult.ok();
    }

    @ApiOperation("刪除註冊名單")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id}/delete")//此註解主要作用是 請情路徑 與 處理請求方法的 映射關係
    public JsonResult delete(@PathVariable Long id) {//需要增加long類型的id
        log.debug("RegController.delete()");
        log.debug("根據id=" + id + "刪除類別");

        regService.deleteById(id);

        return JsonResult.ok();
    }

    // 使用@PathVariable的註解體現 {}佔位符字節
    @ApiOperation("修改註冊名單")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult edit(@PathVariable("id") Long regId, RegEditDTO regEditDTO) {
        log.debug("RegController.edit()");
        log.debug("根據數字id=" + regId + "修改類別");

        return JsonResult.ok("訂單修改成功");
    }


    @ApiOperation("查詢註冊列表")
    @ApiOperationSupport(order = 400)
    @GetMapping//RESTful風格
    public JsonResult list() {
        log.debug("RegController.list()");

        // 調用業務層方法
        List<RegListItemVO> list = regService.list();
        return JsonResult.ok(list);
    }
}

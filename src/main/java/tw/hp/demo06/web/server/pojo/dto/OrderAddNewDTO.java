package tw.hp.demo06.web.server.pojo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tw.hp.demo06.web.server.config.JsonLocalTime;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * DTO類 - 數據傳輸物件(對象)
 * 通常用於在不同層之間傳遞數據
 */

@Data

public class OrderAddNewDTO implements Serializable {

    //required = true必須要有 example=示例值
    @ApiModelProperty(value = "訂單客戶名稱",required = true,example = "MaxLo")
    private String name; //訂單客戶名稱
    @ApiModelProperty(value = "訂單客戶電話",example = "0912-345-678")
    private String phone; //品牌logo
    @ApiModelProperty(value = "訂單客戶的主要聯絡人",example = "新郎 or 新娘")
    private String person; //父級id
    @ApiModelProperty(value = "年齡",example = "100")
    private Integer age; //年齡
    @ApiModelProperty(value = "訂單客戶的line",example = "sr0912345")
    private String line; //Line
    @ApiModelProperty(value = "訂單客戶的line",example = "sr0912345")
    private String email; //emil
    @ApiModelProperty(value = "客戶選定的專案",example = "輕鬆專案")
    private String project; //計畫
    @ApiModelProperty(value = "地點",example = "台北")
    private String location; //想舉辦的地點
    @ApiModelProperty(value = "訂單日期",example = "2023-09-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; //訂單日期
    @ApiModelProperty(value = "如何得知",example = "網路")
    private String how; //如何得知我們
    @ApiModelProperty(value = "想對我們說什麼？)",example = "哈瞜,你好嗎？")
    private String message; //想對我們說什麼？


}

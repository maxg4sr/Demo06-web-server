package tw.hp.demo06.web.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/**
 * DTO類 - 數據傳輸物件(對象)
 * 通常用於在不同層之間傳遞數據
 */

@Data
public class RegAddNewDTO implements Serializable {

    //required = true必須要有 example=示例值
    @ApiModelProperty(value = "訂單客戶名稱",required = true,example = "MaxLo")
    private String name; //訂單客戶名稱
    @ApiModelProperty(value = "訂單客戶電話",example = "0912-345-678")
    private String phone; //品牌logo
    @ApiModelProperty(value = "性別",example = "男 或 女 ")
    private String gender; //性別
    @ApiModelProperty(value = "年齡",example = "100")
    private Integer age; //年齡
    @ApiModelProperty(value = "訂單客戶的line",example = "sr0912345")
    private String line; //Line
    @ApiModelProperty(value = "訂單客戶的email",example = "123@tek.com")
    private String email; //email
    @ApiModelProperty(value = "訂單日期",example = "2023-09-01")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; //訂單日期
}
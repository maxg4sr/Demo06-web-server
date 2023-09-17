package tw.hp.demo06.web.server.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@Data
public class ResumeAddNewDTO implements Serializable {

    //required = true必須要有 example=示例值
    @ApiModelProperty(value = "中文名稱",required = true,example = "李大維")
    private String chineseName;
    @ApiModelProperty(value = "英文名稱",example = "Li Da-Wei")
    private String englishName;
    @ApiModelProperty(value = "出生日期",example = "2001-01-01 ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @ApiModelProperty(value = "住家地址",example = "新北國自大路9527號44之10弄4樓")
    private String currentAddress;
    @ApiModelProperty(value = "電話",example = "0900-000-123")
    private String phoneNumber;
    @ApiModelProperty(value = "email",example = "123@tek.com")
    private String email;
//    @ApiModelProperty(value = "附件",example = "xxx.pdf")
//    private byte[] resume;
}

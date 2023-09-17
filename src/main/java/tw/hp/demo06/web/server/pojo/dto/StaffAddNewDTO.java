package tw.hp.demo06.web.server.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import static tw.hp.demo06.web.server.validation.BrandValidationConst.*;//調用

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * DTO類 - 數據傳輸物件(對象)
 * 通常用於在不同層之間傳遞數據
 */
@Data
public class StaffAddNewDTO implements Serializable {


    //required = true必須要有 example=示例值
    @ApiModelProperty(value = "員工姓名", required = true, example = "MaxLO")
    @NotBlank(message = "請填寫有效的員工姓名")//不能為空
    private String name; //員工姓名
    @ApiModelProperty(value = "員工暱稱", required = true, example = "little Lo")
    @NotBlank(message = "請填寫有效的員工暱稱")
//    @Pattern(regexp = REGEXP_NICKNAME, message = MESSAGE_NICKNAME)
    private String nickname; //員工的暱稱
    @ApiModelProperty(value = "員工的電話", example = "0999-XXX-XXX")
    private String phone; //員工的電話
    @ApiModelProperty(value = "email", example = "9527@abc.tw")
    private String email; //分類id
    @ApiModelProperty(value = "員工的頭像", example = "123.jpg")
    private String avatar; ///員工的頭像
    @ApiModelProperty(value = "簡介", example = "大家好我是Max...")
    private String description; //簡介
    @ApiModelProperty(value = "薪水", example = "NT:23000")
    private String salary; //薪水
    @ApiModelProperty(value = "是否啟用，1=啟用，0=未啟用")
    private Integer enable;
    @ApiModelProperty(value = "自定義排序值(0~99)", example = "1")
    @Range(max = 99)//表示最大範圍
    private Integer sort; //排序值


}

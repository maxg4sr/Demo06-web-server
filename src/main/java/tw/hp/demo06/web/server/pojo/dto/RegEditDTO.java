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
public class RegEditDTO implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String gender;
    private Integer age;
    private String line;
    private String email;
    //    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 指定日期格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 訂單日期
}
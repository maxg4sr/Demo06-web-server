package tw.hp.demo06.web.server.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * VO類 - 數據傳輸物件(對象)
 * 通常封裝特定實體或業務數據的屬性
 */
@Data
public class RegDetailVO implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String gender;
    private Integer age;
    private String line;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
}

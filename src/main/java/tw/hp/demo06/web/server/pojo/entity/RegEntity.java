package tw.hp.demo06.web.server.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class RegEntity implements Serializable {

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

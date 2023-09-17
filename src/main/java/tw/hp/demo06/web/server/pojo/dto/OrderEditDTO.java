package tw.hp.demo06.web.server.pojo.dto;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * DTO類 - 數據傳輸物件(對象)
 * 通常用於在不同層之間傳遞數據
 */
@Data
public class OrderEditDTO implements Serializable {

    private Long id;
    private String name;
    private String person;
    private Integer age;
    private String phone;
    private String line;
    private String email;
    private String project;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String how;
    private String message;

}

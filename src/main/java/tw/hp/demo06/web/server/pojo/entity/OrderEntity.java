package tw.hp.demo06.web.server.pojo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderEntity implements Serializable {

    private Long id;
    private String name;
    private String person;
    private Integer age;
    private String phone;
    private String line;
    private String email;
    private String project;
    private String location;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String how;
    private String message;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 根据需要格式化日期时间
//    private LocalDateTime gmtCreate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 根据需要格式化日期时间
//    private LocalDateTime gmtModified;
}

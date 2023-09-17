package tw.hp.demo06.web.server.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * VO類 - 數據傳輸物件(對象)
 * 通常封裝特定實體或業務數據的屬性
 */
@Data
@JsonSerialize(using = LocalDateTimeSerializer.class)
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
public class OrderDetailVO implements Serializable {

    private Long id; // 對應到表中的 id 列
    private String name; // 對應到表中的 name 列
    private String person; // 對應到表中的 person 列
    private Integer age;
    private String phone; // 對應到表中的 phone 列
    private String line; // 對應到表中的 line 列
    private String email;
    private String project; // 對應到表中的 project 列
    private String location; // 對應到表中的 location 列
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date; // 對應到表中的 datetime 列，使用 LocalDateTime 來表示日期和時間
    private String how; // 對應到表中的 how 列
    private String message; // 對應到表中的 message 列
}

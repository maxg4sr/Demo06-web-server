package tw.hp.demo06.web.server.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "reg")
@Data
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String gender;
    private String age;
    private String line;
    private String email;
    private Date date;
}

package tw.hp.demo06.web.server.pojo.entity;


import lombok.Data;
/**
 * ENTITY（實體類） - 通常用於映射數據庫表格
 */
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StaffEntity implements Serializable {

    private Long id;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String avatar;
    private String description;
    private String salary;
    private Integer enable;
    private Integer sort;
//    private LocalDateTime gmtCreate;
//    private LocalDateTime gmtModified;

}

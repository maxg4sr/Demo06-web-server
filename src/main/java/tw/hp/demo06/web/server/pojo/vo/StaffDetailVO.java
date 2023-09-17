package tw.hp.demo06.web.server.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * VO類 - 數據傳輸物件(對象)
 * 通常封裝特定實體或業務數據的屬性
 */
@Data
public class StaffDetailVO implements Serializable {

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

}

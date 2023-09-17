package tw.hp.demo06.web.server.pojo.dto;

import lombok.Data;

import java.io.Serializable;
/**
 * DTO類 - 數據傳輸物件(對象)
 * 通常用於在不同層之間傳遞數據
 */
@Data
public class StaffEditDTO implements Serializable {

    private Long id;//員工id
    private String name;//員工名稱
    private String nickname;//員工的暱稱
    private String phone;//員工的電話
    private String email;
    private String avatar;
    private String description;
    private String salary;
    private Integer enable;
    private Integer sort;

}

package tw.hp.demo06.web.server.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ResumeListItemVO implements Serializable {

    private Long id;
    private String chineseName;
    private String englishName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDate;
    private String currentAddress;
    private String phoneNumber;
    private String email;
//    private byte[] resume;
}

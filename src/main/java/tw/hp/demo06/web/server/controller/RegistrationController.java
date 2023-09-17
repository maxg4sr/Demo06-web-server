package tw.hp.demo06.web.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tw.hp.demo06.web.server.JPA.RegistrationRepository;
import tw.hp.demo06.web.server.pojo.entity.RegistrationEntity;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationRepository registrationRepository;

    @PostMapping("/reg")
    public String reg(@RequestBody RegistrationEntity registrationEntity){
        try {
            registrationRepository.save(registrationEntity);
            return "請求已經提交";
        }catch (Exception e){
            e.printStackTrace();
            return "服務器內部出現錯誤";
        }
    }
}

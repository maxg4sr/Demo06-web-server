package tw.hp.demo06.web.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import tw.hp.demo06.web.server.pojo.entity.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tw.hp.demo06.web.server.JPA.RequestRepository;

@RestController
public class RequestController {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestBody RequestEntity requestEntity) {
        try {
            // 将表单数据保存到数据库
            requestRepository.save(requestEntity);
            return "請求已經提交";
        } catch (Exception e) {
            e.printStackTrace();
            return "內部服務器出現錯誤";
        }
    }
}
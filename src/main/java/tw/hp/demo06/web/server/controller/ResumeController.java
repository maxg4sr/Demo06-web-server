package tw.hp.demo06.web.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.hp.demo06.web.server.JPA.ResumeRepository;
import tw.hp.demo06.web.server.pojo.entity.ResumeEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ResumeController {

    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeController(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @PostMapping("/submitResume")
    public String submitForm(
            @RequestParam("chineseName") String chineseName,
            @RequestParam("englishName") String englishName,
            @RequestParam("birthDate") String birthDate, // 将日期参数更改为字符串
            @RequestParam("currentAddress") String currentAddress,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email
//            @RequestPart("resume") MultipartFile resume
    ) {
        try {
            // 手动解析日期字符串
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedBirthDate = dateFormat.parse(birthDate);

            ResumeEntity resumeEntity = new ResumeEntity();
            resumeEntity.setChineseName(chineseName);
            resumeEntity.setEnglishName(englishName);
            resumeEntity.setBirthDate(parsedBirthDate); // 设置解析后的日期
            resumeEntity.setCurrentAddress(currentAddress);
            resumeEntity.setPhoneNumber(phoneNumber);
            resumeEntity.setEmail(email);
//            resumeEntity.setResume(resume.getBytes());

            resumeRepository.save(resumeEntity);

            return "請求已經提交";
        } catch (Exception e) {
            e.printStackTrace();
            return "內部服務器出現錯誤";
        }
    }
}
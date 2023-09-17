package tw.hp.demo06.web.server.ex.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.hp.demo06.web.server.ex.ServiceException;
import web.JsonResult;
import web.ServiceCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    // 是一個在Java中用於處理異常的註解。
    // 使用@ExceptionHandler註解，
    // 它告訴Java編譯器該方法是用於處理當前類中可能引發的異常或錯誤的方法。

    public JsonResult handleServiceException(ServiceException e){
        log.error("統一處理ServiceException異常，將向客戶端響應:{}",e.getMessage());
        return JsonResult.fail(e);
    }
    @ExceptionHandler
    public JsonResult handleBindException(BindException e){
        log.error("統一處理BindException異常，將向客戶端響應:{}",e.getMessage());

        // 一個錯誤
//        String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        // 多個錯誤
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

//        StringBuilder builder = new StringBuilder();
//        for(FieldError fieldError : fieldErrors){
//            builder.append(fieldError.getDefaultMessage());
//            builder.append("; "); // 結尾多分號
//        }
//        String message = builder.toString();

        // new StringJoiner(分隔符,[前綴],[後綴])
        StringJoiner joiner = new StringJoiner("; ","錯誤提示：","");
        for(FieldError fieldError : fieldErrors){
            joiner.add(fieldError.getDefaultMessage());
        }
        String message = joiner.toString();
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e){
        log.error("統一處理未明確的異常【{}】，將向客戶端響應:{}",e.getClass().getName(),e.getMessage());
        String message = "服務器繁忙，請聯繫管理員！";
        return JsonResult.fail(ServiceCode.ERR_UNKNOWN,message);
    }

}

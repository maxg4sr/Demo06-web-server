package tw.hp.demo06.web.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // 表示當前類是一個編寫設計切面的類
@Slf4j
@Component
public class DemoAspect {

    // 定義切面 - 指定一個方法
    @Pointcut("execution(public * tw.hp.demo06.web.server.controller.StaffController.list(..))")
    // 這個註解定義了一個方法切面，後面必須跟一個方法的聲明,這個方法就是為了單純定義這個切面的名稱，不需要寫任何代碼
    public void pointCut(){}

    // 織入內容 - 向指定切面的方法前/後添加代碼
    @Before("pointCut()") // 前置通知
    public void before(JoinPoint joinPoint){
        log.debug("前置advice執行...");
        // JoinPoint可以作為advice方法的參數來聲明，JoinPoint對象會包含當前切面的各種信息
        // 經常使用到的是獲取當前切面對象方法的信息
        log.debug("切面方法名稱：{}",joinPoint.getSignature().getName());
    }

    @After("pointCut()") // 後置通知
    public void after(){
        log.debug("後置advice執行...");
    }

    @AfterThrowing("pointCut()") // 異常通知 - 只有目標方法發生異常才會執行
    public void throwing(){
        log.debug("方法發生異常！！！");
    }

    // ProceedingJoinPoint是JoinPoint的子接口，擁有更多的方法，這個參數的功能是可以在環繞advice中調用我們切面的方法
    // 這個方法還要有返回值，因為我們調用的切面的方法可能有返回值，，如果環繞advice不返回這個值，調用者將接收不到這個值
    @Around("pointCut()") // 環繞通知
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("環繞advice，執行之前...");

        // ProceedingJoinPoint類型的參數具有調用切面方法的功能
        Object obj = joinPoint.proceed();

        log.debug("環繞advice，執行之後!!!");
        return obj;
    }



}

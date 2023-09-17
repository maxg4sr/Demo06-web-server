package tw.hp.demo06.web.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimerAspect {

    // 需要統計每個業務方法的執行耗時
    @Around("execution(* tw.hp.demo06.web.server.service.Impl.*.*(..))")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("環繞advice，執行之前...");

        long start = System.currentTimeMillis();

        Object obj = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.debug("當前切面匹配到的組件類：{}",joinPoint.getTarget());
        log.debug("當前切面匹配到的方法：{}",joinPoint.getSignature());
        log.debug("當前切面匹配到的方法的參數列表：{}",joinPoint.getArgs());
        log.debug("當前業務方法執行的耗時：{}",(end-start));

        return obj;
    }


}

package com.atgugu.srpingcloud.servive;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "PaymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String PaymentInfoTimeout(Integer id) {
//        int exe = 10/0;
        Random random = new Random();
        int time = random.nextInt(5);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " PaymentInfoTimeout,id: " + id + "\t" + "等待时常：" + time + "s \t" + "o(╥﹏╥)o";
    }

    public String PaymentInfoTimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " TimeoutHandler,id: " + id + "系统异常或是运行报错：" + "\t" + "o(╥﹏╥)o";
    }
@HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
})
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("不能为负数");
        }
        String serialNumber = IdUtil.fastSimpleUUID();
        return Thread.currentThread().getName() + "\t" + "成功调用流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id 不能为负数，请稍后再试！！！ id: " + id;
    }
}

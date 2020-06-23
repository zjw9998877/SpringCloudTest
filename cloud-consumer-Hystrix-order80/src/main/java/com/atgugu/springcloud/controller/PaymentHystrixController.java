package com.atgugu.springcloud.controller;

import com.atgugu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class PaymentHystrixController {
    @Resource
    private PaymentService paymentService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id) {
        return paymentService.PaymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String PaymentInfoTimeout(@PathVariable Integer id) {
        System.err.println(11112233);
        return paymentService.PaymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutHandler(@PathVariable Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " TimeoutHandler,id: " + id + "消费端系统异常或是运行报错：" + "\t" + "o(╥﹏╥)o";
    }

    public String paymentGlobalFallbackMethod() {
        return "系统繁忙稍后再试";
    }

}

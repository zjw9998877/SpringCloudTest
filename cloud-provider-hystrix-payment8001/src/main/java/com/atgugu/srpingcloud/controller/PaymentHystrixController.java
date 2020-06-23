package com.atgugu.srpingcloud.controller;

import com.atgugu.srpingcloud.servive.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentService paymentService;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id) {
        return paymentService.PaymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfoTimeout(@PathVariable Integer id) {
        return paymentService.PaymentInfoTimeout(id);
    }

    @GetMapping("/payment/hystrix/timeoutHandler/{id}")
    public String PaymentInfoTimeoutHandler(@PathVariable Integer id) {
        return paymentService.PaymentInfoTimeoutHandler(id);
    }
}

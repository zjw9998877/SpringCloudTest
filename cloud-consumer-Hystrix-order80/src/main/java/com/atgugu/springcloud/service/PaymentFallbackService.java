package com.atgugu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String PaymentInfo_OK(Integer id) {
        return "PaymentFallbackService_PaymentInfo_OK is shutdown!!!!,/(ㄒoㄒ)/~~  id: " + id;
    }

    @Override
    public String PaymentInfoTimeout(Integer id) {
        return "PaymentFallbackService_PaymentInfoTimeout is shutdown!!!!,/(ㄒoㄒ)/~~ id: " + id;
    }
}

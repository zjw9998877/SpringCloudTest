package com.atgugu.springcloud.controller;

import com.atgugu.springcloud.entities.CommonResult;
import com.atgugu.springcloud.entities.Payment;
import com.atgugu.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class ConsumerController {

    private String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("into consumer create");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("into consumer getPaymentById");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping("consumer/payment/lb")
    public String getPaymentLoadBalance() {

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        //当前接口访问的次数%注册的微服务的总数的下标的服务
        if (serviceInstances == null || serviceInstances.size() <= 0) {
            log.error("当前没有服务注册");
        }
        ServiceInstance instances = loadBalance.instances(serviceInstances);
        URI uri = instances.getUri();
        System.err.println(uri);
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}

package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@FeignClient(name = "notification", url = "http://localhost:8984/notifications")
public interface ServiceFeignClient {
    @GetMapping
    public Set m2();

    @PostMapping("/{userName}")
    public String m(@PathVariable String userName);
}

package com.pigorv.springcloud.orders;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.toList;

//@AllArgsConstructor
@RequestMapping("orders")
@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();



  // @Autowired
           // @Qualifier("com.epam.springcloud.OrderClient")
     RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    public OrdersController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ServiceFeignClient serviceFeignClient;

    @GetMapping
    public String health() {
        System.out.println("serviceFeignClient:  "+serviceFeignClient.m2());
        return "OK";

    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {

        ServiceInstance usersInfo = discoveryClient.getInstances("users").get(0);
        String hostName = usersInfo.getHost();
        int port = usersInfo.getPort();

serviceFeignClient.m(order.getUserName());
//        try {
//            System.out.println("woof");
//            restTemplate.getForObject("http://" + hostName + ":" + port + "/" + order.getUserName(), UserDto.class);
//            System.out.println("woof2");
//        } catch (RestClientException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            restTemplate.put("http://localhost:8183/" + order.getProduct(), null);
//        } catch (RestClientException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        orderList.add(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

//    @GetMapping("/users/{userName}")
//    public List<String> getProductsForUser(@PathVariable String userName) {
//        return orderList.stream()
//                .filter(order -> userName.equals(order.getUserName()))
//                .map(Order::getProduct)
//                .collect(toList());
//    }

    @GetMapping("/users/{name}")
    List<String> getProducts(@PathVariable String name) {
        return orderList.stream()
                .filter(order -> name.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

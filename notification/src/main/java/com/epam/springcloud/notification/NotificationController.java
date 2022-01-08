package com.epam.springcloud.notification;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping("/notifications")
@RestController
public class NotificationController {
    private final static Set<Notification> notifications = new HashSet<>();

    public Notification notify(String user) {
        return new Notification(user);
    }

    public List<Notification> getNotifications() {
        return Collections.emptyList();
    }

    @PostMapping("/{userName}")
    public String m(@PathVariable String userName){
        notifications.add(notify(userName));
        System.out.println(notifications);
        return "Added";
    }

    @GetMapping
    public Set<Notification> m2(){
        return notifications;
    }

//    @GetMapping
//    public Set<String> m2(){
//        return Set.of( "notifications");
//    }




    @PostMapping   //("/cat22")
    public String m2(@RequestBody String s){
        System.out.println(s);
        return "Meow2";
    }
}

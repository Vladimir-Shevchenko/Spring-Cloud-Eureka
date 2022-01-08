package com.epam.springcloud.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Notification {
    String user;


    Notifier notifyBy = Notifier.EMAIL;

    public  Notification(){}
    public  Notification(String user){
        this.user=user;
    }

    enum Notifier {
        EMAIL
    }
}

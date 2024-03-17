package com.company.dabawalla.service;

import com.company.dabawalla.entities.Mess;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendNotification(Mess mess) {
        JavaMailServiceImpl smtp = new JavaMailServiceImpl();
        smtp.send(mess.getMessEmail(), "New Order", "You have received a new order");
    }
}

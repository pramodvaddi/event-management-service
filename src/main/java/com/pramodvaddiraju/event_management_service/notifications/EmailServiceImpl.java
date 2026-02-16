package com.pramodvaddiraju.event_management_service.notifications;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{


    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEventCreatedMail(String to, String eventTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Event Created Successfully");
        message.setText("Your event have been created successfully");

        javaMailSender.send(message);

    }

    @Override
    public void sendReminderEmail(String to, String eventTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Event Reminder");
        message.setText("Reminder: Your event is happening soon. " + eventTitle);

        javaMailSender.send(message);
    }
}

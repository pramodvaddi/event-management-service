package com.pramodvaddiraju.event_management_service.notifications;

public interface EmailService {

    void sendEventCreatedMail(String to, String eventTitle);
    void sendReminderEmail(String to, String eventTitle);
}

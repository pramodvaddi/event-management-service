package com.pramodvaddiraju.event_management_service.notifications;

import com.pramodvaddiraju.event_management_service.entity.Event;
import com.pramodvaddiraju.event_management_service.repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class EventReminderScheduler {
    private final EventRepository eventRepository;
    private final EmailService emailService;

    public EventReminderScheduler(EventRepository eventRepository,
                                  EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void sendReminders() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextHour = now.plusHours(1);

        List<Event> events =
                eventRepository.findByEventDateBetween(now, nextHour);

        for (Event event : events) {
            emailService.sendReminderEmail(
                    event.getOrganiserEmail(),
                    event.getTitle()
            );
        }
    }

}

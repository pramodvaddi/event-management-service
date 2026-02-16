package com.pramodvaddiraju.event_management_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventRequest {

    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @NotBlank(message = "location cannot be blank")
    private String location;
    @NotNull(message = "date and time cannot be blank")
    private LocalDateTime eventDate;
    @Email(message = "Valid email must be provided")
    @NotBlank(message = "email cannot be null")
    private String organiserEmail;

    public EventRequest(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getOrganiserEmail() {
        return organiserEmail;
    }

    public void setOrganiserEmail(String organiserEmail) {
        this.organiserEmail = organiserEmail;
    }
}

package com.pramodvaddiraju.event_management_service.repository;

import com.pramodvaddiraju.event_management_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {



}

package com.borisdvlpr.ticketline.service;

import com.borisdvlpr.ticketline.domain.CreateEventRequest;
import com.borisdvlpr.ticketline.domain.UpdateEventRequest;
import com.borisdvlpr.ticketline.domain.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerID, CreateEventRequest event);

    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);

    Optional<Event> getEventForOrganizer(UUID eventId, UUID organizerId);

    Event updateEventForOrganizer(UUID eventId, UUID organizerId, UpdateEventRequest event);

    void deleteEventForOrganizer(UUID eventId, UUID organizerId);
}

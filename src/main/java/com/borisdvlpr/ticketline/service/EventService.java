package com.borisdvlpr.ticketline.service;

import com.borisdvlpr.ticketline.domain.CreateEventRequest;
import com.borisdvlpr.ticketline.domain.entity.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerID, CreateEventRequest event);
}

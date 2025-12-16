package com.borisdvlpr.ticketline.service.impl;

import com.borisdvlpr.ticketline.domain.CreateEventRequest;
import com.borisdvlpr.ticketline.domain.entity.Event;
import com.borisdvlpr.ticketline.domain.entity.TicketType;
import com.borisdvlpr.ticketline.domain.entity.User;
import com.borisdvlpr.ticketline.exception.UserNotFoundException;
import com.borisdvlpr.ticketline.repository.EventRepository;
import com.borisdvlpr.ticketline.repository.UserRepository;
import com.borisdvlpr.ticketline.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerID, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerID).orElseThrow(() ->
                new UserNotFoundException(String.format("User with ID '%s' not found.", organizerID)));

        Event eventToCreate = new Event();

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);

                    return ticketTypeToCreate;

                }).toList();

        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizerId, pageable);
    }
}

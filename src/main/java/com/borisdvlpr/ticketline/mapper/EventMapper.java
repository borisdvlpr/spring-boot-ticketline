package com.borisdvlpr.ticketline.mapper;

import com.borisdvlpr.ticketline.domain.CreateEventRequest;
import com.borisdvlpr.ticketline.domain.CreateTicketTypeRequest;
import com.borisdvlpr.ticketline.domain.UpdateEventRequest;
import com.borisdvlpr.ticketline.domain.UpdateTicketTypeRequest;
import com.borisdvlpr.ticketline.domain.dto.*;
import com.borisdvlpr.ticketline.domain.entity.Event;
import com.borisdvlpr.ticketline.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);

    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

    UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);

    UpdateEventRequest fromDto(UpdateEventRequestDto dto);

    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

    ListPublishedEventsResponseDto toListPublishedEventsResponseDto(Event event);
}

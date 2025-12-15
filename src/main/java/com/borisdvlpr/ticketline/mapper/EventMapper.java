package com.borisdvlpr.ticketline.mapper;

import com.borisdvlpr.ticketline.domain.CreateEventRequest;
import com.borisdvlpr.ticketline.domain.CreateTicketTypeRequest;
import com.borisdvlpr.ticketline.domain.dto.CreateEventRequestDto;
import com.borisdvlpr.ticketline.domain.dto.CreateEventResponseDto;
import com.borisdvlpr.ticketline.domain.dto.CreateTicketTypeRequestDto;
import com.borisdvlpr.ticketline.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}

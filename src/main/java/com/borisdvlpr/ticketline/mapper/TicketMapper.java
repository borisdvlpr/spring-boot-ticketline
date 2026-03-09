package com.borisdvlpr.ticketline.mapper;

import com.borisdvlpr.ticketline.domain.dto.ListTicketResponseDto;
import com.borisdvlpr.ticketline.domain.dto.ListTicketTicketTypeResponseDto;
import com.borisdvlpr.ticketline.domain.entity.Ticket;
import com.borisdvlpr.ticketline.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    ListTicketTicketTypeResponseDto toListTicketTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);
}

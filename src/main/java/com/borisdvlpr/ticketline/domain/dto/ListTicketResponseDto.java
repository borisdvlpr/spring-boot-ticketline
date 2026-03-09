package com.borisdvlpr.ticketline.domain.dto;

import com.borisdvlpr.ticketline.domain.type.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTicketResponseDto {
    private UUID id;
    private TicketStatusEnum statusEnum;
    private ListTicketTicketTypeResponseDto ticketType;
}

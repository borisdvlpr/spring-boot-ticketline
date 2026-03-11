package com.borisdvlpr.ticketline.domain.dto;

import com.borisdvlpr.ticketline.domain.type.TicketValidationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketValidationResponseDto {
    private UUID id;
    private TicketValidationStatusEnum status;
}

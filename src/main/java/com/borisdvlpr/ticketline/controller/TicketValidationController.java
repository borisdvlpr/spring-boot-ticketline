package com.borisdvlpr.ticketline.controller;

import com.borisdvlpr.ticketline.domain.dto.TicketValidationRequestDto;
import com.borisdvlpr.ticketline.domain.dto.TicketValidationResponseDto;
import com.borisdvlpr.ticketline.domain.entity.TicketValidation;
import com.borisdvlpr.ticketline.domain.type.TicketValidationMethodEnum;
import com.borisdvlpr.ticketline.mapper.TicketValidationMapper;
import com.borisdvlpr.ticketline.service.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/ticket-validations")
public class TicketValidationController {
    private final TicketValidationService ticketValidationService;
    private final TicketValidationMapper ticketValidationMapper;

    @PostMapping
    public ResponseEntity<TicketValidationResponseDto> validateTicket(
            @RequestBody TicketValidationRequestDto ticketValidationRequestDto
    ) {
        TicketValidationMethodEnum method = ticketValidationRequestDto.getMethod();

        TicketValidation ticketValidation;
        if (method.equals(TicketValidationMethodEnum.MANUAL)) {
            ticketValidation = ticketValidationService.validateTicketManually(ticketValidationRequestDto.getId());

         } else {
            ticketValidation = ticketValidationService.validateTicketByQrCode(ticketValidationRequestDto.getId());
        }

        return ResponseEntity.ok(ticketValidationMapper.toTicketValidationResponseDto(ticketValidation));
    }
}

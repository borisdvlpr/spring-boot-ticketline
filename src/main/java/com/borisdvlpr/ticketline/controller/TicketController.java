package com.borisdvlpr.ticketline.controller;

import com.borisdvlpr.ticketline.domain.dto.ListTicketResponseDto;
import com.borisdvlpr.ticketline.mapper.TicketMapper;
import com.borisdvlpr.ticketline.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.borisdvlpr.ticketline.util.JwtUtils.parseUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @GetMapping
    public Page<ListTicketResponseDto> listTickets(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ) {
        return ticketService.listTicketsForUser(parseUserId(jwt), pageable)
                .map(ticketMapper::toListTicketResponseDto);
    }
}

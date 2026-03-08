package com.borisdvlpr.ticketline.service;

import com.borisdvlpr.ticketline.domain.entity.Ticket;

import java.util.UUID;

public interface TicketTypeService {
    Ticket purchaseTicket(UUID userId, UUID ticketType);
}

package com.borisdvlpr.ticketline.repository;

import com.borisdvlpr.ticketline.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    int coundByTicketTypeId(UUID ticketTypeId);
}

package com.borisdvlpr.ticketline.repository;

import com.borisdvlpr.ticketline.domain.entity.TicketValidation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketValidationRepository extends JpaRepository<TicketValidation, UUID> {

}

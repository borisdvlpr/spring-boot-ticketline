package com.borisdvlpr.ticketline.service.impl;

import com.borisdvlpr.ticketline.domain.entity.Ticket;
import com.borisdvlpr.ticketline.domain.entity.TicketType;
import com.borisdvlpr.ticketline.domain.entity.User;
import com.borisdvlpr.ticketline.domain.type.TicketStatusEnum;
import com.borisdvlpr.ticketline.exception.TicketTypeNotFoundException;
import com.borisdvlpr.ticketline.exception.TicketsSoldOutException;
import com.borisdvlpr.ticketline.exception.UserNotFoundException;
import com.borisdvlpr.ticketline.repository.TicketRepository;
import com.borisdvlpr.ticketline.repository.TicketTypeRepository;
import com.borisdvlpr.ticketline.repository.UserRepository;
import com.borisdvlpr.ticketline.service.QrCodeService;
import com.borisdvlpr.ticketline.service.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("User with ID %s was not found.", userId)
        ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
                .orElseThrow(() -> new TicketTypeNotFoundException(
                        String.format("Ticket type with ID %s was not found.", ticketTypeId)
                ));

        int purchasedTickets = ticketRepository.coundByTicketTypeId(ticketTypeId);
        Integer totalAvailable = ticketType.getTotalAvailable();

        if (purchasedTickets + 1 > totalAvailable) {
            throw new TicketsSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }
}

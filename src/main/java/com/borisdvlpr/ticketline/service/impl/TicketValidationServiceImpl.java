package com.borisdvlpr.ticketline.service.impl;

import com.borisdvlpr.ticketline.domain.entity.QrCode;
import com.borisdvlpr.ticketline.domain.entity.Ticket;
import com.borisdvlpr.ticketline.domain.entity.TicketValidation;
import com.borisdvlpr.ticketline.domain.type.QrCodeStatusEnum;
import com.borisdvlpr.ticketline.domain.type.TicketValidationMethodEnum;
import com.borisdvlpr.ticketline.domain.type.TicketValidationStatusEnum;
import com.borisdvlpr.ticketline.exception.QrCodeNotFoundException;
import com.borisdvlpr.ticketline.exception.TicketNotFoundException;
import com.borisdvlpr.ticketline.repository.QrCodeRepository;
import com.borisdvlpr.ticketline.repository.TicketRepository;
import com.borisdvlpr.ticketline.repository.TicketValidationRepository;
import com.borisdvlpr.ticketline.service.TicketValidationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TicketValidationServiceImpl implements TicketValidationService {
    private final TicketRepository ticketRepository;
    private final QrCodeRepository qrCodeRepository;
    private final TicketValidationRepository ticketValidationRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
        QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
                .orElseThrow(() -> new QrCodeNotFoundException(
                        String.format("QR code with ID %s was not found.", qrCodeId)
                ));

        Ticket ticket = qrCode.getTicket();

        return validateTicket(ticket, TicketValidationMethodEnum.QR_SCAN);
    }

    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        return validateTicket(ticket, TicketValidationMethodEnum.MANUAL);
    }

    private TicketValidation validateTicket(Ticket ticket, TicketValidationMethodEnum validationMethod) {
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(validationMethod);

        TicketValidationStatusEnum validationStatus = ticket.getValidations().stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);

        ticketValidation.setStatus(validationStatus);

        return ticketValidationRepository.save(ticketValidation);
    }
}

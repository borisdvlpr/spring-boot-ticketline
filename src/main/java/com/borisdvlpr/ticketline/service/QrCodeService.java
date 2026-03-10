package com.borisdvlpr.ticketline.service;

import com.borisdvlpr.ticketline.domain.entity.QrCode;
import com.borisdvlpr.ticketline.domain.entity.Ticket;

import java.util.UUID;

public interface QrCodeService {
    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket(UUID ticketId, UUID userId);
}

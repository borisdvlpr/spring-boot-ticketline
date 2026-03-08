package com.borisdvlpr.ticketline.service;

import com.borisdvlpr.ticketline.domain.entity.QrCode;
import com.borisdvlpr.ticketline.domain.entity.Ticket;

public interface QrCodeService {
    QrCode generateQrCode(Ticket ticket);
}

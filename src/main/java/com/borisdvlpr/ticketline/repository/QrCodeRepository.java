package com.borisdvlpr.ticketline.repository;

import com.borisdvlpr.ticketline.domain.entity.QrCode;
import com.borisdvlpr.ticketline.domain.type.QrCodeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
    Optional<QrCode> findByTicketIdAndTicketPurchaserId(UUID ticketId, UUID purchaserId);

    Optional<QrCode> findByIdAndStatus(UUID id, QrCodeStatusEnum status);
}

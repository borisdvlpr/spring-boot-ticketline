package com.borisdvlpr.ticketline.domain;

public interface TicketTypeRequest {
    String getName();
    Double getPrice();
    String getDescription();
    Integer getTotalAvailable();
}

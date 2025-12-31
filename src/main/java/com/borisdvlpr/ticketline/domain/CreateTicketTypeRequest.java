package com.borisdvlpr.ticketline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequest implements TicketTypeRequest {
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
}

package com.example.PnrTicket2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AviaCompanyDto {
    private Long id;
    private String airlineName;
    private String iataCode;
    private String flightNumber;
    private LocalDate rdt;
}

package com.example.PnrTicket2.dto;

import lombok.Data;

@Data
public class AviaCompanyDto {
    private Long id;
    private String airlineName;
    private String iataCode;
    private String flightNumber;
}

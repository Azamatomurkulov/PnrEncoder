package com.example.PnrTicket2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AirportDto {
    private Long id;
    private String iataCode;
    private String airport;
    private String country;
    private LocalDate rdt;
}

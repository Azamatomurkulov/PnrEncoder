package com.example.PnrTicket2.dto;

import lombok.Data;

@Data
public class DepartureCityDto {
    private Long id;

    private String iataCode;
    private String airport;
    private String country;

}

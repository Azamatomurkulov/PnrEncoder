package com.example.PnrTicket2.dto;

import lombok.Data;

@Data
public class ArrivalCityDto {
    private Long id;
    private String iataCode;
    private String city;
    private String country;
}

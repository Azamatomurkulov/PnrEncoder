package com.example.PnrTicket2.dto;

import com.example.PnrTicket2.entity.Airport;
import com.example.PnrTicket2.entity.AviaCompany;
import com.example.PnrTicket2.entity.DateOfDeparture;
import lombok.Data;

@Data
public class PnrDto {
    private Long id;

    private AviaCompany aviaCompany;

    private DateOfDeparture dateOfDeparture;
    private Airport arrivalAirport;
    private Airport departureAirport;
    private String terminal;
    private String departureTime;
    private String arrivalTime;
    private String dayOfDeparture;
    private String typeOfAirPlane;
    private String typeOfTicket;
}

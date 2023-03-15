package com.example.PnrTicket2.dto;

import com.example.PnrTicket2.entity.ArrivalCity;
import com.example.PnrTicket2.entity.AviaCompany;
import com.example.PnrTicket2.entity.DateOfDeparture;
import com.example.PnrTicket2.entity.DepartureCity;
import lombok.Data;

import javax.persistence.OneToOne;

@Data
public class PnrDto {
    private Long id;

    private AviaCompany aviaCompany;

    private DateOfDeparture dateOfDeparture;

    private DepartureCity departureCity;

    private ArrivalCity arrivalCity;
    private String terminal;
    private String departureTime;
    private String arrivalTime;
    private String dayOfDeparture;
    private String typeOfAirPlane;
    private String typeOfTicket;
}

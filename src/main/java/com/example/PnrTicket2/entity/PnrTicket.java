package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PnrTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private AviaCompany aviaCompany;
    @OneToOne
    private DateOfDeparture dateOfDeparture;
    @OneToOne
    private Airport arrivalAirport;
    @OneToOne
    private Airport departureAirport;
    private String terminal;
    private String departureTime;
    private String arrivalTime;
    private String dayOfDeparture;
    private String typeOfAirPlane;
    private String typeOfTicket;


}

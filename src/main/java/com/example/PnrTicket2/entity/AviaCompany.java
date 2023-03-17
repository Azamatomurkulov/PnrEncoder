package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AviaCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String airlineName;
    @Column(nullable = false,unique = true)
    private String iataCode;

    private String flightNumber;
    private LocalDate rdt;
}

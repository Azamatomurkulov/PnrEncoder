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
    @Column(length = 30)
    private String airlineName;
    @Column(nullable = false,unique = true)
    private String iataCode;
    @Column(length = 30)
    private String flightNumber;
    private LocalDate rdt;
}

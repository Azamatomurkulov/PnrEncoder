package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String iataCode;
    private String airport;
    private String country;
}

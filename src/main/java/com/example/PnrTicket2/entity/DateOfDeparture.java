package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateOfDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String date;
    @Column(unique = true)
    private String dateCode;
    private String dateEncode;

}

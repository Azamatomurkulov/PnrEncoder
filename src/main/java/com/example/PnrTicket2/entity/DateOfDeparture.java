package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateOfDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String date;

    private String dateCode;
    private String dateEncode;

}

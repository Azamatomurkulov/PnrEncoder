package com.example.PnrTicket2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.transform.sax.SAXTransformerFactory;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartureCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String iataCode;
    private String airport;
    private String country;

}

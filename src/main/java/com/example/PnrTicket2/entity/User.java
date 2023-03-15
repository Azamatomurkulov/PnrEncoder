package com.example.PnrTicket2.entity;

import com.example.PnrTicket2.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    private LocalDate rdt;

}

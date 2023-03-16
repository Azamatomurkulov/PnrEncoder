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
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true,length = 30)
    private String login;
    @Column(nullable = false,length = 30)
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    private LocalDate rdt;

}

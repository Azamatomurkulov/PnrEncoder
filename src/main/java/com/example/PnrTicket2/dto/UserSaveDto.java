package com.example.PnrTicket2.dto;

import com.example.PnrTicket2.enums.Roles;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class UserSaveDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    private LocalDate rdt;
}

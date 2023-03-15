package com.example.PnrTicket2.dto;

import com.sun.istack.NotNull;

public class UserDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String matchingPassword;
}

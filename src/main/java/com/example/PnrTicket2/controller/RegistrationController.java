package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;

public class RegistrationController {
    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
}

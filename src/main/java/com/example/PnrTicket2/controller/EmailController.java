package com.example.PnrTicket2.controller;


import com.example.PnrTicket2.service.DefaultEmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    DefaultEmailService emailService;

    @GetMapping(value = "/simple-email/{user-email}")
    public void sendSimpleEmail(@PathVariable("user-email") String email) {
            emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
    }


}
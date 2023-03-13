package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.service.PnrTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PnrTicketController {
    PnrTicketService pnrTicketService;

    @GetMapping("/pnr/{pnr}")
    String pnrEncode(@PathVariable String pnr){
        return pnrTicketService.PnrEncode(pnr);
    }
}

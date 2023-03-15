package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.service.PnrTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PnrTicketController {
    PnrTicketService pnrTicketService;

    @GetMapping("/pnr/{pnr}")
    String pnrEncode(@PathVariable String pnr){
        return pnrTicketService.PnrEncode(pnr);
    }

    @GetMapping("/pnr/moder")
    List<String> pnrEncoderModer(@RequestParam String pnr){
        return pnrTicketService.PnrEncoderForModer(pnr);
    }
}

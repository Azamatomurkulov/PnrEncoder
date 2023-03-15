package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.entity.PnrTicket;
import com.example.PnrTicket2.exceptions.AviaCompanyException;
import com.example.PnrTicket2.service.PnrTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pnr")
public class PnrTicketController {
    PnrTicketService pnrTicketService;

    @GetMapping("/{pnr}")
    String pnrEncode(@PathVariable String pnr) throws Exception{
        return pnrTicketService.pnrEncode(pnr);
    }

    @GetMapping("/moder")
    List<String> pnrEncoderModer(@RequestParam String pnr) throws Exception {
        return pnrTicketService.pnrEncoderForModer(pnr);
    }
}

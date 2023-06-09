package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.PnrDto;
import com.example.PnrTicket2.service.PnrTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pnr")
public class PnrTicketController {
    PnrTicketService pnrTicketService;
    @GetMapping("/ticket/{id}")
    PnrDto getPnrById(@PathVariable Long id){
        return pnrTicketService.getPnrById(id);
    }

    @GetMapping("/all")
    List<PnrDto> getAllPnrTickets(){
        return pnrTicketService.getAllPnrTickets();
    }

    @PostMapping("/addNew")
    PnrDto addNewPnrTicket(PnrDto dto){
        return pnrTicketService.addNewPnr(dto);
    }

    @DeleteMapping("/delete/{id}")
    String deletePnrById(@PathVariable Long id){
        return pnrTicketService.deletePnrById(id);
    }

    @GetMapping("/{pnr}")
    String pnrEncode(@PathVariable String pnr) throws Exception{
        return pnrTicketService.pnrEncode(pnr);
    }

    @GetMapping("/moder")
    List<String> pnrEncoderModer(@RequestParam String pnr) throws Exception {
        return pnrTicketService.pnrEncoderForModer(pnr);
    }
}

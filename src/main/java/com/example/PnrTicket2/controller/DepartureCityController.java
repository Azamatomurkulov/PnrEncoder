package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.ArrivalCityDto;
import com.example.PnrTicket2.dto.DepartureCityDto;
import com.example.PnrTicket2.service.DepartureCityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departureCity")
public class DepartureCityController {
    DepartureCityService departureCityService;


    @GetMapping("/{id}")
    DepartureCityDto getDepartureCityById(@PathVariable Long id){
        return departureCityService.getDepartureCityById(id);
    }
    @GetMapping("/all")
    List<DepartureCityDto> getAllDepartureCities(){
        return departureCityService.getAllDepartureCities();
    }
    @PostMapping("/addNewCity")
    DepartureCityDto addNewDepartureCity(@RequestBody DepartureCityDto dto){
        return departureCityService.addNewDepartureCity(dto);
    }
    @DeleteMapping("/delete/{id}")
    String deleteCityById(@PathVariable Long id){
        return departureCityService.deleteDepartureCityById(id);
    }
}

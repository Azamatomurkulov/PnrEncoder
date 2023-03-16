package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.AirportDto;
import com.example.PnrTicket2.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/airport")
public class AirportController {

    AirportService airportService;

    @GetMapping("/{id}")
    AirportDto getArrivalCityById(@PathVariable Long id){
        return airportService.getAirportById(id);
    }

    @GetMapping("/all")
    List<AirportDto> getAllArrivalCities(){
        return airportService.getAllAirports();
    }

    @PostMapping("/addNewAirport")
    AirportDto addNewArrivalCity(@RequestBody AirportDto dto){
        return airportService.addNewAirport(dto);
    }

    @PutMapping("/update/{id}")
    AirportDto updateAirportById(@PathVariable Long id,
                                 @RequestBody AirportDto dto){
        return airportService.updateAirport(id,dto);
    }

    @PutMapping("/delete/{id}")
    String deleteCityById(@PathVariable Long id){
        return airportService.deleteAirportById(id);
    }
}

package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.ArrivalCityDto;
import com.example.PnrTicket2.service.ArrivalCityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/arrivalCity")
public class ArrivalCityController {

    ArrivalCityService arrivalCityService;

    @GetMapping("/{id}")
    ArrivalCityDto getArrivalCityById(@PathVariable Long id){
        return arrivalCityService.getArrivalCityById(id);
    }
    @GetMapping("/all")
    List<ArrivalCityDto> getAllArrivalCities(){
        return arrivalCityService.getAllArrivalCities();
    }
    @PostMapping("/addNewCity")
    ArrivalCityDto addNewArrivalCity(@RequestBody ArrivalCityDto dto){
        return arrivalCityService.addNewArrivalCity(dto);
    }
    @DeleteMapping("/delete/{id}")
    String deleteCityById(@PathVariable Long id){
        return arrivalCityService.deleteArrivalCityById(id);
    }
}

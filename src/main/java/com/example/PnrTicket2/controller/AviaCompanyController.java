package com.example.PnrTicket2.controller;

import com.example.PnrTicket2.dto.AviaCompanyDto;
import com.example.PnrTicket2.service.AviaCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/aviaCompany")
public class AviaCompanyController {

    AviaCompanyService aviaCompanyService;

    @GetMapping("/{id}")
    AviaCompanyDto getAviaCompanyById(@PathVariable Long id){
        return aviaCompanyService.getAviaCompanyById(id);
    }
    @GetMapping("/all")
    List<AviaCompanyDto> getAllAviaCompanies(){
        return aviaCompanyService.getAllAviaCompanies();
    }
    @PostMapping("/addNewCompany")
    AviaCompanyDto addNewAviaCompany(@RequestBody AviaCompanyDto dto){
        return aviaCompanyService.addNewAviaCompany(dto);
    }
    @PutMapping("/update/{id}")
    AviaCompanyDto updateAviaCompanyById(@PathVariable Long id,
                                         @RequestBody AviaCompanyDto dto){
        return aviaCompanyService.updateAviaCompanyById(id,dto);
    }
    @PutMapping("/delete/{id}")
    String deleteCompanyById(@PathVariable Long id){
        return aviaCompanyService.deleteAviaCompanyById(id);
    }
}

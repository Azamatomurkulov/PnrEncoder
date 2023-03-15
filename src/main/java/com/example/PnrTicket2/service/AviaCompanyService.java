package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.ArrivalCityDto;
import com.example.PnrTicket2.dto.AviaCompanyDto;
import com.example.PnrTicket2.entity.ArrivalCity;
import com.example.PnrTicket2.entity.AviaCompany;
import com.example.PnrTicket2.repository.AviaCompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AviaCompanyService {
    AviaCompanyRepository aviaCompanyRepository;

    public AviaCompanyDto getAviaCompanyById(Long id){
        AviaCompany company = aviaCompanyRepository.findById(id).get();
        return entityToDto(company);
    }

    public AviaCompanyDto entityToDto(AviaCompany company){
        AviaCompanyDto dto = new AviaCompanyDto();
        dto.setId(company.getId());
        dto.setAirlineName(company.getAirlineName());
        dto.setFlightNumber(company.getFlightNumber());
        dto.setIataCode(company.getIataCode());
        return dto;
    }
    public List<AviaCompanyDto> getAllAviaCompanies(){
        List<AviaCompany> companies = aviaCompanyRepository.findAll();
        List<AviaCompanyDto> dtos = new ArrayList<>();
        for(AviaCompany company: companies){
            dtos.add(entityToDto(company));
        }return dtos;
    }
    public AviaCompanyDto addNewAviaCompany(AviaCompanyDto dto){
        AviaCompany company = new AviaCompany();
        company.setAirlineName(dto.getAirlineName());
        company.setFlightNumber(dto.getFlightNumber());
        company.setIataCode(dto.getIataCode());
        company = aviaCompanyRepository.save(company);
        dto.setId(company.getId());
        return dto;
    }
    public String deleteAviaCompanyById(Long id){
        aviaCompanyRepository.deleteById(id);
        return "Avia company with id: " +id+ " has been deleted.";
    }
}

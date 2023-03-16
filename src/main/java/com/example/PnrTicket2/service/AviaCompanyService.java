package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.AviaCompanyDto;
import com.example.PnrTicket2.entity.AviaCompany;
import com.example.PnrTicket2.repository.AviaCompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AviaCompanyService {
    AviaCompanyRepository aviaCompanyRepository;

    public AviaCompanyDto getAviaCompanyById(Long id){
        AviaCompany company = aviaCompanyRepository.findByIdAndRdtIsNull(id);
        return entityToDto(company);
    }

    public AviaCompanyDto entityToDto(AviaCompany company){
        AviaCompanyDto dto = new AviaCompanyDto();
        dto.setId(company.getId());
        dto.setAirlineName(company.getAirlineName());
        dto.setFlightNumber(company.getFlightNumber());
        dto.setIataCode(company.getIataCode());
        dto.setRdt(company.getRdt());
        return dto;
    }
    public List<AviaCompanyDto> getAllAviaCompanies(){
        List<AviaCompany> companies = aviaCompanyRepository.findAllByRdtIsNull();
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

    public AviaCompanyDto updateAviaCompanyById(Long id, AviaCompanyDto dto){
        AviaCompany aviaCompany = aviaCompanyRepository.findByIdAndRdtIsNull(id);
        if(dto.getAirlineName()!=null){
            aviaCompany.setAirlineName(dto.getAirlineName());
        }
        if(dto.getFlightNumber()!=null){
            aviaCompany.setFlightNumber(dto.getFlightNumber());
        }
        if(dto.getIataCode()!=null){
            aviaCompany.setIataCode(dto.getIataCode());
        }
        aviaCompanyRepository.save(aviaCompany);
        dto.setId(aviaCompany.getId());
        dto = entityToDto(aviaCompany);
        return dto;

    }
    public String deleteAviaCompanyById(Long id){
        AviaCompany aviaCompany = aviaCompanyRepository.findById(id).get();
        aviaCompany.setRdt(LocalDate.now());
        aviaCompanyRepository.save(aviaCompany);
        return "Avia company with id: " +id+ " has been deleted.";
    }
}

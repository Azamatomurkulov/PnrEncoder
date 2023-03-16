package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.ArrivalCityDto;
import com.example.PnrTicket2.dto.DepartureCityDto;
import com.example.PnrTicket2.entity.ArrivalCity;
import com.example.PnrTicket2.entity.DepartureCity;
import com.example.PnrTicket2.repository.DepartureCityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartureCityService {
    DepartureCityRepository departureCityRepository;

    public DepartureCityDto getDepartureCityById(Long id){
        DepartureCity city = departureCityRepository.findById(id).get();
        return entityToDto(city);
    }

    public DepartureCityDto entityToDto(DepartureCity city){
        DepartureCityDto dto = new DepartureCityDto();
        dto.setId(city.getId());
        dto.setAirport(city.getAirport());
        dto.setCountry(city.getCountry());
        dto.setIataCode(city.getIataCode());
        return dto;
    }
    public List<DepartureCityDto> getAllDepartureCities(){
        List<DepartureCity> cities = departureCityRepository.findAll();
        List<DepartureCityDto> dtos = new ArrayList<>();
        for(DepartureCity city: cities){
            dtos.add(entityToDto(city));
        }return dtos;
    }
    public DepartureCityDto addNewDepartureCity(DepartureCityDto dto){
        DepartureCity city = new DepartureCity();
        city.setAirport(dto.getAirport());
        city.setCountry(dto.getCountry());
        city.setIataCode(dto.getIataCode());
        city = departureCityRepository.save(city);
        dto.setId(city.getId());
        return dto;
    }
    public String deleteDepartureCityById(Long id){
        departureCityRepository.deleteById(id);
        return "Departure city with id: " +id+ " has been deleted.";
    }
}

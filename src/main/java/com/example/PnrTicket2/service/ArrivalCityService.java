package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.ArrivalCityDto;
import com.example.PnrTicket2.entity.ArrivalCity;
import com.example.PnrTicket2.repository.ArrivalCityRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArrivalCityService {
    ArrivalCityRepository arrivalCityRepository;

    public ArrivalCityDto getArrivalCityById(Long id){
        ArrivalCity city = arrivalCityRepository.findById(id).get();
        return entityToDto(city);
    }

    public ArrivalCityDto entityToDto(ArrivalCity city){
        ArrivalCityDto dto = new ArrivalCityDto();
        dto.setId(city.getId());
        dto.setAirport(city.getAirport());
        dto.setCountry(city.getCountry());
        dto.setIataCode(city.getIataCode());
        return dto;
    }
    public List<ArrivalCityDto> getAllArrivalCities(){
        List<ArrivalCity> cities = arrivalCityRepository.findAll();
        List<ArrivalCityDto> dtos = new ArrayList<>();
        for(ArrivalCity city: cities){
            dtos.add(entityToDto(city));
        }return dtos;
    }
    public ArrivalCityDto addNewArrivalCity(ArrivalCityDto dto){
        ArrivalCity city = new ArrivalCity();
        city.setAirport(dto.getAirport());
        city.setCountry(dto.getCountry());
        city.setIataCode(dto.getIataCode());
        city = arrivalCityRepository.save(city);
        dto.setId(city.getId());
        return dto;
    }
    public String deleteArrivalCityById(Long id){
        arrivalCityRepository.deleteById(id);
        return "Arrival city with id: " +id+ " has been deleted.";
    }
}

package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.AirportDto;
import com.example.PnrTicket2.entity.Airport;
import com.example.PnrTicket2.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {
    AirportRepository airportRepository;

    public AirportDto getAirportById(Long id){
        Airport city = airportRepository.findByIdAndRdtIsNull(id);
        return entityToDto(city);
    }

    public AirportDto entityToDto(Airport city){
        AirportDto dto = new AirportDto();
        dto.setId(city.getId());
        dto.setAirport(city.getAirport());
        dto.setCountry(city.getCountry());
        dto.setIataCode(city.getIataCode());
        dto.setRdt(city.getRdt());
        return dto;
    }
    public List<AirportDto> getAllAirports(){
        List<Airport> cities = airportRepository.findAllByRdtIsNull();
        List<AirportDto> dtos = new ArrayList<>();
        for(Airport city: cities){
            dtos.add(entityToDto(city));
        }return dtos;
    }
    public AirportDto addNewAirport(AirportDto dto){
        Airport city = new Airport();
        city.setAirport(dto.getAirport());
        city.setCountry(dto.getCountry());
        city.setIataCode(dto.getIataCode());
        city = airportRepository.save(city);
        dto.setId(city.getId());
        return dto;
    }
    public AirportDto updateAirport(Long id,AirportDto dto){
        Airport airport = airportRepository.findByIdAndRdtIsNull(id);
        if(dto.getAirport()!=null){
            airport.setAirport(dto.getAirport());
        }
        if(dto.getCountry()!=null){
            airport.setCountry(dto.getCountry());
        }
        if(dto.getIataCode()!=null){
            airport.setIataCode(dto.getIataCode());
        }

        airportRepository.save(airport);
        dto.setId(airport.getId());
        dto = entityToDto(airport);
        return dto;

    }
    public String deleteAirportById(Long id){
        Airport airport = airportRepository.findById(id).get();
        airport.setRdt(LocalDate.now());
        airportRepository.save(airport);
        return "Arrival city with id: " +id+ " has been deleted.";
    }
}

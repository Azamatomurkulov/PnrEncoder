package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.PnrDto;
import com.example.PnrTicket2.entity.*;

import com.example.PnrTicket2.exceptions.AviaCompanyException;
import com.example.PnrTicket2.exceptions.CityException;
import com.example.PnrTicket2.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PnrTicketService {
    PnrTicketRepository pnrTicketRepository;
    DateOfDepartureRepository dateOfDeparture;
    AirportRepository airportRepository;
    AviaCompanyRepository aviaCompanyRepository;

    public PnrDto getPnrById(Long id){
        PnrTicket pnr = pnrTicketRepository.findById(id).get();
        return entityToDto(pnr);
    }

    public List<PnrDto> getAllPnrTickets(){
        List<PnrTicket> pnrTickets = pnrTicketRepository.findAll();
        List<PnrDto> dtos = new ArrayList<>();
        for(PnrTicket pnr: pnrTickets){
            dtos.add(entityToDto(pnr));
        }
        return dtos;
    }
    public PnrDto addNewPnr(PnrDto dto){
        PnrTicket pnrTicket = new PnrTicket();

        pnrTicket.setDepartureAirport(dto.getDepartureAirport());
        pnrTicket.setArrivalAirport(dto.getArrivalAirport());
        pnrTicket.setArrivalTime(dto.getArrivalTime());
        pnrTicket.setDepartureTime(dto.getDepartureTime());
        pnrTicket.setTerminal(dto.getTerminal());
        pnrTicket.setAviaCompany(dto.getAviaCompany());
        pnrTicket.setTypeOfTicket(dto.getTypeOfTicket());
        pnrTicket.setTypeOfAirPlane(dto.getTypeOfAirPlane());
        pnrTicket.setDayOfDeparture(dto.getDayOfDeparture());
        pnrTicket.setDateOfDeparture(dto.getDateOfDeparture());

         pnrTicketRepository.save(pnrTicket);
         return entityToDto(pnrTicket);
    }

    public String deletePnrById(Long id){
        pnrTicketRepository.deleteById(id);
        return "Pnr with id: "+id+" been deleted.";
    }
    public PnrDto pnrEncode(String pnr) throws Exception {
        String[] substring = pnr.trim().split("\\s+");

        AviaCompany aviaCompany = new AviaCompany();

        PnrTicket pnrTicket = new PnrTicket();

        int n = 0;
        if(substring[0].length()<3){
            aviaCompany.setIataCode(substring[0]);
            aviaCompany.setFlightNumber(substring[0]+substring[1]);
            n=1;
        } else
            aviaCompany.setFlightNumber(substring[0]);
            aviaCompany.setIataCode(substring[0].substring(0,2));



        String city = substring[4+n];

        String[] cities = city.split("(?<=\\G.{" + 3 + "})");

        StringBuffer st = new StringBuffer();

        st.append(substring[6+n]);
        st.insert(2,":");

        StringBuffer st2 = new StringBuffer();
        st2.append(substring[7+n]);
        st2.insert(2,":");

        DateOfDeparture dateOfDeparture = new DateOfDeparture();
        dateOfDeparture.setDateCode(substring[2+n]);
        dateOfDeparture.setDate(substring[2+n].substring(0,2));


        Airport departureAirport = new Airport();
        departureAirport.setIataCode(cities[0]);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setIataCode(cities[1]);



        pnrTicket.setAviaCompany(aviaCompany);
        pnrTicket.setDateOfDeparture(dateOfDeparture);
        pnrTicket.setDepartureAirport(departureAirport);
        pnrTicket.setArrivalAirport(arrivalAirport);
        pnrTicket.setTerminal(substring[5+n]);
        pnrTicket.setDepartureTime(st.toString());
        pnrTicket.setArrivalTime(st2.toString());

        PnrTicket pnrTicket2 = pnrDateEncoder(pnrTicket);
        pnrTicket2 = pnrCityEncoder(pnrTicket2);

        pnrTicket2 = pnrAviaCompanyEncoder(pnrTicket2);

        pnrTicketRepository.save(pnrTicket2);


        PnrDto dto = entityToDto(pnrTicket2);
        return dto;
//
//        return pnrTicket2.getAviaCompany().getAirlineName()+" "+
//                pnrTicket2.getDateOfDeparture().getDate()+" "+
//                pnrTicket2.getDateOfDeparture().getDateEncode() + " "+
//                pnrTicket2.getDepartureAirport().getAirport()+" "+
//                pnrTicket2.getArrivalAirport().getAirport()+" "+
//                pnrTicket2.getTerminal()+" " +
//                pnrTicket2.getDepartureTime()+" "+
//                pnrTicket2.getArrivalTime()+" "+
//                pnrTicket2.getAviaCompany().getFlightNumber();
    }

    public PnrTicket pnrDateEncoder(PnrTicket pnr){
        String date = pnr.getDateOfDeparture().getDateCode();

        date = date.substring(date.length()-3);

        DateOfDeparture dateOfDeparture3 = (dateOfDeparture.findByDateCode(date));
        dateOfDeparture3.setDate(pnr.getDateOfDeparture().getDate());
        pnr.setDateOfDeparture(dateOfDeparture3);
        return pnr;
    }

    public PnrTicket pnrCityEncoder(PnrTicket pnr)throws CityException {

        String iataDeparture = pnr.getDepartureAirport().getIataCode();
        String iataArrival = pnr.getArrivalAirport().getIataCode();
        Airport departireAirport;
        Airport arrivalAirport;

        if(( departireAirport = airportRepository.findByIataCode(iataDeparture))==null){
            throw new CityException("Город не найден, убедитесь в правильности ввода.");
        }
        if(( arrivalAirport = airportRepository.findByIataCode(iataArrival))==null){
            throw new CityException("Город не найден, убедитесь в правильности ввода.");
        }

        pnr.setDepartureAirport(departireAirport);
        pnr.setArrivalAirport(arrivalAirport);
        return pnr;
    }

    public PnrTicket pnrAviaCompanyEncoder(PnrTicket pnr)throws AviaCompanyException{
        AviaCompany aviaCompany;
        if( (aviaCompany = aviaCompanyRepository.findByIataCode(pnr.getAviaCompany().getIataCode()))==null){
            throw new AviaCompanyException("Авиакомпания не найдена, убедитесь в праильности ввода.");
        }
        aviaCompany.setFlightNumber(pnr.getAviaCompany().getFlightNumber());
        pnr.setAviaCompany(aviaCompany);
        return pnr;
    }

    public List<PnrDto> pnrEncoderForModer(String pnr) throws Exception {
        String[] pnrString = pnr.split("/");
        List<PnrDto> list = new ArrayList<>();
        for(int i =0;i< pnrString.length;i++){
            PnrDto str  = pnrEncode(pnrString[i]);
            list.add(str);
        }return list;

    }

    private PnrDto entityToDto(PnrTicket pnr){
        PnrDto dto = new PnrDto();
        dto.setId(pnr.getId());
        dto.setDepartureAirport(pnr.getDepartureAirport());
        dto.setArrivalAirport(pnr.getArrivalAirport());
        dto.setArrivalTime(pnr.getArrivalTime());
        dto.setDepartureTime(pnr.getDepartureTime());
        dto.setTerminal(pnr.getTerminal());
        dto.setAviaCompany(pnr.getAviaCompany());
        dto.setTypeOfTicket(pnr.getTypeOfTicket());
        dto.setTypeOfAirPlane(pnr.getTypeOfAirPlane());
        dto.setDayOfDeparture(pnr.getDayOfDeparture());
        dto.setDateOfDeparture(pnr.getDateOfDeparture());
        return dto;
    }
}

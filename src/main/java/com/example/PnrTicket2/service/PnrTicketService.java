package com.example.PnrTicket2.service;

import com.example.PnrTicket2.dto.PnrDto;
import com.example.PnrTicket2.entity.*;

import com.example.PnrTicket2.exceptions.AviaCompanyException;
import com.example.PnrTicket2.exceptions.CityException;
import com.example.PnrTicket2.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PnrTicketService {
    PnrTicketRepository pnrTicketRepository;
    DateOfDepartureRepository dateOfDeparture;

    DepartureCityRepository departureCityRepository;
    ArrivalCityRepository arrivalCityRepository;
    AviaCompanyRepository aviaCompanyRepository;
    public String pnrEncode(String pnr) throws Exception {
        String[] substring = pnr.trim().split("\\s+");

        AviaCompany aviaCompany = new AviaCompany();

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

        DepartureCity departureCity = new DepartureCity();
        departureCity.setIataCode(cities[0]);

        ArrivalCity arrivalCity = new ArrivalCity();
        arrivalCity.setIataCode(cities[1]);

        PnrTicket pnrTicket = new PnrTicket();
        pnrTicket.setAviaCompany(aviaCompany);
        pnrTicket.setDateOfDeparture(dateOfDeparture);
        pnrTicket.setDepartureCity(departureCity);
        pnrTicket.setArrivalCity(arrivalCity);
        pnrTicket.setTerminal(substring[5+n]);
        pnrTicket.setDepartureTime(st.toString());
        pnrTicket.setArrivalTime(st2.toString());

        PnrTicket pnrTicket2 = pnrDateEncoder(pnrTicket);
        pnrTicket2 = pnrCityEncoder(pnrTicket2);

        pnrTicket2 = pnrAviaCompanyEncoder(pnrTicket2);

//        return pnrTicket2;

        return pnrTicket2.getAviaCompany().getAirlineName()+" "+
                pnrTicket2.getDateOfDeparture().getDate()+" "+
                pnrTicket2.getDateOfDeparture().getDateEncode() + " "+
                pnrTicket2.getDepartureCity().getAirport()+" "+
                pnrTicket2.getArrivalCity().getAirport()+" "+
                pnrTicket2.getTerminal()+" " +
                pnrTicket2.getDepartureTime()+" "+
                pnrTicket2.getArrivalTime()+" "+
                pnrTicket2.getAviaCompany().getFlightNumber();
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

        String iataDeparture = pnr.getDepartureCity().getIataCode();
        String iataArrival = pnr.getArrivalCity().getIataCode();
        DepartureCity departureCity;
        ArrivalCity arrivalCity;

        if(( departureCity = departureCityRepository.findByIataCode(iataDeparture))==null){
            throw new CityException("Город не найден, убедитесь в правильности ввода.");
        }
        if(( arrivalCity = arrivalCityRepository.findByIataCode(iataArrival))==null){
            throw new CityException("Город не найден, убедитесь в правильности ввода.");
        }

        pnr.setDepartureCity(departureCity);
        pnr.setArrivalCity(arrivalCity);
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

    public List<String> pnrEncoderForModer(String pnr) throws Exception {
        String[] pnrString = pnr.split("/");
        List<String> list = new ArrayList<>();
        for(int i =0;i< pnrString.length;i++){
            String str  = pnrEncode(pnrString[i]);
            list.add(str);
        }return list;

    }

    private PnrDto entityToDto(PnrTicket pnr){
        PnrDto dto = new PnrDto();
        dto.setId(pnr.getId());
        dto.setArrivalCity(pnr.getArrivalCity());
        dto.setDepartureCity(pnr.getDepartureCity());
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

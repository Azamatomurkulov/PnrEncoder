package com.example.PnrTicket2.service;

import com.example.PnrTicket2.entity.*;

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
    public String PnrEncode(String pnr){
        String[] substring = pnr.trim().split("\\s+");

        AviaCompany aviaCompany = new AviaCompany();

        int n = 0;
        if(substring[0].length()<3){
            aviaCompany.setIataCode(substring[0]);
            aviaCompany.setFlightNumber(substring[0]+substring[1]);
            n=1;
        }else
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

        PnrTicket pnrTicket2 = PnrDateEncoder(pnrTicket);
        pnrTicket2 = PnrCityEncoder(pnrTicket2);
        pnrTicket2 = PnrAviaCompanyEncoder(pnrTicket2);
//        return pnrTicket;
//        String number = pnrTicket2.getDateOfDeparture().getDateCode();
//        number = number.substring(0,2)+" "+pnrTicket2.getDateOfDeparture().getDateEncode();
//
        return pnrTicket2.getAviaCompany().getAirlineName()+" "+
                pnrTicket2.getDateOfDeparture().getDate()+" "+
                pnrTicket2.getDateOfDeparture().getDateEncode() + " "+
                pnrTicket2.getDepartureCity().getCity()+" "+
                pnrTicket2.getArrivalCity().getCity()+" "+
                pnrTicket2.getTerminal()+" " +
                pnrTicket2.getDepartureTime()+" "+
                pnrTicket2.getArrivalTime()+" "+
                pnrTicket2.getAviaCompany().getFlightNumber();
    }
//    public static void main(String[] args){
//        PnrTicketService pnr = new PnrTicketService();
//        PnrTicket pnr2 =  pnr.PnrEncode("AC8097 Y 15OCT 4 YVRSEA HK1         1335 1435   DH4 E 0");
//
//        String company = pnr2.getAviaCompany();
//        company = company.substring(0,2);
//        System.out.println(company);
//
//        String date = pnr2.getDateOfDeparture();
//        date = date.substring(date.length()-3);
//        System.out.println(date);
//
//    }
    public PnrTicket PnrDateEncoder(PnrTicket pnr){
//        DateOfDeparture dateOfDeparture2 = pnr.getDateOfDeparture();
//        String date = dateOfDeparture2.getDateCode();
        String date = pnr.getDateOfDeparture().getDateCode();

        date = date.substring(date.length()-3);

        DateOfDeparture dateOfDeparture3 = (dateOfDeparture.findByDateCode(date));
        dateOfDeparture3.setDate(pnr.getDateOfDeparture().getDate());
        pnr.setDateOfDeparture(dateOfDeparture3);
        return pnr;
    }

    public PnrTicket PnrCityEncoder(PnrTicket pnr){

        String iataDeparture = pnr.getDepartureCity().getIataCode();
        String iataArrival = pnr.getArrivalCity().getIataCode();
        DepartureCity departureCity = departureCityRepository.findByIataCode(iataDeparture);
        ArrivalCity arrivalCity = arrivalCityRepository.findByIataCode(iataArrival);

        pnr.setDepartureCity(departureCity);
        pnr.setArrivalCity(arrivalCity);
        return pnr;
    }

    public PnrTicket PnrAviaCompanyEncoder(PnrTicket pnr){
        AviaCompany aviaCompany = aviaCompanyRepository.findByIataCode(pnr.getAviaCompany().getIataCode());
        aviaCompany.setFlightNumber(pnr.getAviaCompany().getFlightNumber());
        pnr.setAviaCompany(aviaCompany);
        return pnr;
    }

    public List<String> PnrEncoderForModer(String pnr){
        String[] pnrString = pnr.split("\r?\n|\r");
        List<String> list = new ArrayList<>();
        for(int i =0;i< pnrString.length;i++){
            String str  = PnrEncode(pnrString[i]);
            list.add(str);
        }return list;

    }
}

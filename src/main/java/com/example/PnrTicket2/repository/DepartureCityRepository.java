package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.DepartureCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureCityRepository extends JpaRepository<DepartureCity,Long> {

    DepartureCity findByIataCode(String iata);
}

package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.ArrivalCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalCityRepository extends JpaRepository<ArrivalCity,Long> {
    ArrivalCity findByIataCode(String iata);
}

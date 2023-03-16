package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.Airport;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Airport findByIataCode(String iata);

    Airport findByIdAndRdtIsNull(Long id);

    List<Airport> findAllByRdtIsNull();
}

package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.DateOfDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateOfDepartureRepository extends JpaRepository<com.example.PnrTicket2.entity.DateOfDeparture,Long> {
    DateOfDeparture findByDateCode(String dateCode);
        }

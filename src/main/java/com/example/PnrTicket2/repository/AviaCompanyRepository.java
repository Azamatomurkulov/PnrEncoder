package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.AviaCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AviaCompanyRepository extends JpaRepository<AviaCompany,Long> {
    AviaCompany findByIataCode(String iata);
}

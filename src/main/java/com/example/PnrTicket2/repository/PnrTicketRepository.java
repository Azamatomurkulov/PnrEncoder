package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.PnrTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PnrTicketRepository extends JpaRepository<PnrTicket, Long> {
}

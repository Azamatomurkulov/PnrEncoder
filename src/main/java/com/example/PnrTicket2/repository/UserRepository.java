package com.example.PnrTicket2.repository;

import com.example.PnrTicket2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from users_table where name = ?", nativeQuery = true)
    User findByName(String name);

    Optional<User> findByIdAndRdtIsNull(Long id);
    List<User> findAllByRdtIsNull();
}

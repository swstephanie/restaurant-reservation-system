package com.sw.restaurant.dao;

import com.sw.restaurant.pojo.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    Optional<Reservation> findById(String id);

}

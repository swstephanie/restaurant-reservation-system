package com.sw.restaurant.dao;

import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.pojo.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {


    Optional<Reservation> findById(String id);
    List<Reservation> findAllByCustomerIn(List<Customer> customerList);



    List<Reservation> findAllByTimeslot(String timeslot);
}

package com.sw.restaurant.service;


import com.sw.restaurant.pojo.Reservation;

import java.util.List;

public interface ReservationServiceInterface {

    Reservation createReservationByEmail(String email, Reservation reservation);


    void deleteReservationById(String id);

    Reservation updateReservation(Reservation reservation) throws IllegalAccessException;
    Reservation getReservationById(String id);
    List<Reservation> getReservationByCustomerName(String customerName);
    List<Reservation> getAllReservations();

}

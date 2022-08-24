package com.sw.restaurant.service;


import com.sw.restaurant.pojo.Reservation;

import java.util.List;

public interface ReservationServiceInterface {

    Reservation createReservation(int partySize, String timeslot);


    void deleteReservationById(String id);

    void updateReservationById(String id, int partySize, String timeslot);
    Reservation getReservationById(String id);
    List<Reservation> getReservationByCustomerName(String customerName);
    List<Reservation> getAllReservations();

}

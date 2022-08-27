package com.sw.restaurant.service;

import com.sw.restaurant.dao.ReservationRepository;
import com.sw.restaurant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements ReservationServiceInterface{
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(int partySize, String timeslot) {
        //Check if the reservation is available
        Reservation reservation = new Reservation();
        reservation.setParty_size(partySize);
        reservation.setTimeslot(timeslot);
        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public void deleteReservationById(String id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservationRepository.delete(reservation);
            reservationRepository.save(reservation);
        } else throw new RuntimeException("Delete reservation failed. id: " + id);


    }

    @Override
    public void updateReservationById(String id, int partySize, String timeslot) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservation.setParty_size(partySize);
            reservation.setTimeslot(timeslot);
            reservationRepository.save(reservation);
        } else throw new RuntimeException("Update reservation failed. id: " + id);
    }

    @Override
    public Reservation getReservationById(String id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            return optionalReservation.get();
        } else throw new RuntimeException("Get reservation failed. id: " + id);
    }

    @Override
    public List<Reservation> getReservationByCustomerName(String customerName) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        if(reservationList.isEmpty())
            throw new RuntimeException("Get reservations failed. No reservation in database. ");
        else return reservationList;

    }
}

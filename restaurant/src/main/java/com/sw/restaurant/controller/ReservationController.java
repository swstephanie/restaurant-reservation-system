package com.sw.restaurant.controller;

import com.sw.restaurant.dao.ReservationRepository;
import com.sw.restaurant.pojo.Reservation;
import com.sw.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "allReservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }
    @RequestMapping(value = "/reservation/new", method = RequestMethod.POST)
    public String makeReservation(@RequestParam(value = "email") String customer_email, @RequestBody Reservation reservation){
        Reservation r = reservationService.createReservationByEmail(customer_email, reservation);
        if(r!=null) return reservation.toString();
        else return "No suitable tables are available now.";
    }
    @RequestMapping(value = "reservation/byId",method = RequestMethod.GET)
    public Reservation getReservationById(@RequestParam(value = "id") String id){
        return reservationService.getReservationById(id);
    }
    @RequestMapping(value = "reservation/byCustomerName",method = RequestMethod.GET)
    public List<Reservation> getReservationByCustomerName(@RequestParam(value = "customer_name") String customer_name){
        return reservationService.getReservationByCustomerName(customer_name);
    }



    @RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
    public String updateReservationsById(@RequestBody Reservation reservation) throws IllegalAccessException {
        Reservation res = reservationService.updateReservation(reservation);
        if(res ==null) return "No suitable tables are available now. Please change a timeslot";
        else return "Successfully update the reservation. id: "+reservation.getId();
    }
    @RequestMapping(value = "reservation/delete",method = RequestMethod.DELETE)
    public String deleteReservationById(@RequestParam(value = "id") String id){
        reservationService.deleteReservationById(id);
        return "Delete the reservation successfully. ID: " + id;
    }

//    Reservation createReservation(int partySize, String timeslot);
//
//
//    void deleteReservationById(String id);
//
//    void updateReservationById(String id, int partySize, String timeslot);
//    Reservation getReservationById(String id);
//    void getReservationByCustomerName(String customerName);
//    List<Reservation> getAllReservation();

}

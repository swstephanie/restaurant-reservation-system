package com.sw.restaurant.service;

import com.sw.restaurant.dao.CustomerRepository;
import com.sw.restaurant.dao.DiningTableRepository;
import com.sw.restaurant.dao.ReservationRepository;
import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.pojo.DiningTable;
import com.sw.restaurant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ReservationService implements ReservationServiceInterface{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DiningTableRepository diningTableRepository;


    private DiningTable findTableByTimeslot(Reservation reservation) {
        List<Reservation> reservationList = reservationRepository.findAllByTimeslot(reservation.getTimeslot());
        List<DiningTable> tableList = diningTableRepository.findAll();
        if(!reservationList.isEmpty()) {
            List<String> table_id_list = new ArrayList<>();
            for (Reservation res: reservationList) table_id_list.add(res.getTable_id());
            tableList = diningTableRepository.findAllByIdNotInAndCapacityBetween(table_id_list, reservation.getParty_size(), reservation.getParty_size() + 2);
        }

        if(!tableList.isEmpty()) return tableList.get(new Random().nextInt(tableList.size()));
        else return null;
    }
    @Override
    @Cacheable(cacheNames = "reservation",key = "#result.id")
    public Reservation createReservationByEmail(String email, Reservation reservation) {
        //Get customer_id
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(!optionalCustomer.isPresent()) throw new RuntimeException("Create new reservations failed because email does not exist in customer database.");
        //Check if the reservation is available ---> find an available table
        if(reservation.getParty_size()==0) throw new RuntimeException("Create new reservations failed because party size is 0.");
        if(reservation.getTimeslot()==null) throw new RuntimeException("Create new reservations failed because timeslot is not provided.");
        DiningTable table = findTableByTimeslot(reservation);

        if(table!=null) {
            String new_table_id = table.getId();
            reservation.setTable_id(new_table_id);
            reservation.setCustomer(optionalCustomer.get());
            reservationRepository.save(reservation);
            return reservation;
        } else return null;

            //throw new RuntimeException("No suitable tables are available now.");

    }

    @Override
    @CacheEvict(cacheNames = "reservation",key = "#id")
    public void deleteReservationById(String id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            reservationRepository.deleteById(id);
        } else throw new RuntimeException("Delete reservation failed. id: " + id);


    }

    @Override
    @CachePut(cacheNames = "reservation",key = "#reservation.id")
    public Reservation updateReservation(Reservation reservation) throws IllegalAccessException {
        if(reservation.getId()==null) throw new RuntimeException("Update reservation failed. Please provide reservation id. ");

        String id = reservation.getId();
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            Reservation old = optionalReservation.get();
            Field[] fields = reservation.getClass().getDeclaredFields();
            for(Field field: fields) {
                field.setAccessible(true);
                if (field.get(reservation) != null && (!field.getName().equals("id"))) {
                    field.set(old, field.get(reservation));
                }
                if (field.getName().equals("table_id")) {
                    if(reservation.getParty_size()==0) reservation.setParty_size(old.getParty_size());
                    DiningTable availableTable = findTableByTimeslot(reservation);
                    if(availableTable==null) return null;
                    field.set(old,availableTable.getId());
                }
            }
            reservationRepository.save(old);
            return old;

        } else throw new RuntimeException("Update reservation failed. No such reservation. Id: " + id + " does not exist");
    }

    @Override
    @Cacheable(cacheNames = "reservation", key="#id")
    public Reservation getReservationById(String id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            return optionalReservation.get();
        } else throw new RuntimeException("Get reservation failed. id: " + id);
    }

    @Override
    public List<Reservation> getReservationByCustomerName(String customerName) {
        List<Customer> customerList = customerRepository.findByCustomerName(customerName);
        if(!customerList.isEmpty()) {
            List<Reservation> reservationList = reservationRepository.findAllByCustomerIn(customerList);
            return reservationList;
        } else throw new RuntimeException("Get Reservation by customer name failed. No such customer in database. Customer Name: "+ customerName);

    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList;

    }
}

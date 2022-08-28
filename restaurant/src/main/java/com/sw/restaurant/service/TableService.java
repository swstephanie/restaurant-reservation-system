package com.sw.restaurant.service;

import com.sw.restaurant.dao.DiningTableRepository;
import com.sw.restaurant.dao.ReservationRepository;
import com.sw.restaurant.pojo.DiningTable;
import com.sw.restaurant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TableService implements TableServiceInterface {

    @Autowired
    DiningTableRepository diningTableRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public List<DiningTable> createTables(int partySize, int num) {
        return null;
    }

    @Override
    public List<DiningTable> getAllTables() {
        return diningTableRepository.findAll();
    }


    @Override
    public List<DiningTable> getAllAvailableTables(String timeslot, int party_size) {
        List<Reservation> reservationList = reservationRepository.findAllByTimeslot(timeslot);
        List<DiningTable> tableList = diningTableRepository.findAll();
        if(!reservationList.isEmpty()) {
            List<String> table_id_list = new ArrayList<>();
            for (Reservation res: reservationList) table_id_list.add(res.getTable_id());
            tableList = diningTableRepository.findAllByIdNotInAndCapacityBetween(table_id_list, party_size, party_size + 2);
        }
        if(!tableList.isEmpty()) return tableList;
        else return null;
    }

    @Override
    public void deleteTables(int partySize, int num) {

    }


}

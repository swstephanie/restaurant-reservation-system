package com.sw.restaurant.service;

import com.sw.restaurant.pojo.DiningTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements TableServiceInterface {

    @Override
    public List<DiningTable> createTables(int partySize, int num) {
        return null;
    }

    @Override
    public List<DiningTable> getAllTables() {
        return null;
    }

    @Override
    public List<DiningTable> getAllAvailableTables() {
        return null;
    }

    @Override
    public void deleteTables(int partySize, int num) {

    }


}

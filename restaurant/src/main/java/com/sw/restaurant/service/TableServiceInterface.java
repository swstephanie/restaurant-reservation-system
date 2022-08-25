package com.sw.restaurant.service;

import com.sw.restaurant.pojo.DiningTable;

import java.util.List;

public interface TableServiceInterface {
    List<DiningTable> createTables(int partySize, int num);

    List<DiningTable> getAllTables();
    List<DiningTable> getAllAvailableTables();

    void deleteTables(int partySize, int num);

}

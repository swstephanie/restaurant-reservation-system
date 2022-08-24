package com.sw.restaurant.service;

import com.sw.restaurant.pojo.DiningTable;

public interface TableServiceInterface {
    DiningTable createTables(int partySize, int num);
    void deleteTables(int partySize, int num);
    DiningTable getAllTables();
    DiningTable getAllAvailableTables();

}

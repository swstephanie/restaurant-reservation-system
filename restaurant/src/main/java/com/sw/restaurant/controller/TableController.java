package com.sw.restaurant.controller;

import com.sw.restaurant.pojo.DiningTable;
import com.sw.restaurant.pojo.Reservation;
import com.sw.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TableController {
    @Autowired
    private TableService tableService;
    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public String getTables(){
        return "tableService";
    }
    @RequestMapping(value = "/admin/allTables", method = RequestMethod.GET)
    public List<DiningTable> getAllTables(){
        return tableService.getAllTables();
    }
    @RequestMapping(value = "admin/getAllAvailableTables",method = RequestMethod.GET)
    public List<DiningTable> getAllAvailable(@RequestBody Reservation reservation){
        if(reservation.getTimeslot()==null || reservation.getParty_size()==0) return null;
        return tableService.getAllAvailableTables(reservation.getTimeslot(),reservation.getParty_size());
    }
}

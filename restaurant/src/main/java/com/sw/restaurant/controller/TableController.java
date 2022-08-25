package com.sw.restaurant.controller;

import com.sw.restaurant.pojo.DiningTable;
import com.sw.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

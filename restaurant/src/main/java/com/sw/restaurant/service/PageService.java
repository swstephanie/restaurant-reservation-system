package com.sw.restaurant.service;

import com.sw.restaurant.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    CustomerRepository customerRepository;


}

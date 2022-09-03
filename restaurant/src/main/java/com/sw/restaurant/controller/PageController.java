package com.sw.restaurant.controller;

import com.sw.restaurant.dao.CustomerRepository;
import com.sw.restaurant.dao.ReservationRepository;
import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class PageController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @GetMapping(value = "reservation/new")
    public String newReservation(HttpServletRequest httpServletRequest) {

        return "insert_reservation";
    }

    @GetMapping(value= {"dashboard","customer/update"})
    public String getDashboard(Principal principal, Model mode, HttpServletRequest request){
        ///move to service tier
        StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();
        String email = principal.getName();
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            mode.addAttribute("user",customer);
            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            Collection<Reservation> reservations = reservationRepository.findAllByCustomerIn(customerList);
            mode.addAttribute("reservations",reservations);
            if(url.contains("dashboard")) return "dashboard";
            return "edit_dashboard";
        } throw new RuntimeException("The customer does not exist in our data base.");
    }
    


}

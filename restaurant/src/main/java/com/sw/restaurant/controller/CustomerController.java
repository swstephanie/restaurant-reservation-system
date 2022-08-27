package com.sw.restaurant.controller;

import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(){
        return "Welcome to Restaurant Reservation Website!";
    }
    //Page for Administrator
    @RequestMapping(value = "/admin/hello",method = RequestMethod.GET)
    public String adminHello(){
        return "Hello, Admin!";
    }
    @RequestMapping(value = "/admin/allCustomers",method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @RequestMapping(value = "/admin/allCustomers/page",method = RequestMethod.GET)
    public Page<Customer> getAllCustomersPagination(@RequestParam(value = "size") Integer size,
                                                    @RequestParam(value = "page") Integer page){
        return customerService.getAllCustomerPagination(page, size);
    }


    //Page for Users
    @RequestMapping(value = "/user/hello",method = RequestMethod.GET)
    public String userHello(){
        return "Hello, User!";
    }

    @RequestMapping(value="customer", method = RequestMethod.GET)
    public Customer getCustomerByEmail(@RequestParam(value = "email") String email) {
        return customerService.getCustomerByEmail(email);
    }
    @RequestMapping(value = "customer/{name}",method = RequestMethod.GET)
    public List<Customer> getCustomerByName(@PathVariable(value = "name") String name){
        return customerService.getCustomerByName(name);
    }
    @RequestMapping(value = "customer/new",method = RequestMethod.POST)
    public String createNewCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return "Create new customer successfully!";
    }

    @RequestMapping(value = "customer/update_name")
    public String updateCustomerName(@RequestParam(value = "email") String email,
                                     @RequestParam(value = "customer_name") String customerName) {
        customerService.updateCustomerName(email,customerName);
        return "Update the customer name successfully! email: " + email;
    }
    @RequestMapping(value = "customer/update_dob")
    public String updateCustomerDob(@RequestParam(value = "email") String email,
                                    @RequestParam(value = "dob") Date dob) {
        customerService.updateCustomerDob(email,dob);
        return "Update the customer dob successfully! email: " + email;
    }
    @RequestMapping(value = "customer/update_gender")
    public String updateCustomerGender(@RequestParam(value = "email") String email,
                                       @RequestParam(value = "gender") String gender) {
        customerService.updateCustomerGender(email,gender);
        return "Update the customer gender successfully! email: " + email;
    }

    @RequestMapping(value = "customer/update_points")
    public String updateCustomerPoints(@RequestParam(value = "email") String email,
                                       @RequestParam(value = "points") int points) {
        customerService.updateCustomerPoints(email,points);
        return "Update the customer points successfully! email: " + email;
    }
    @RequestMapping(value = "customer/delete",method = RequestMethod.DELETE)
    public String deleteCustomerByEmail(@RequestParam(value = "email") String email) {
        customerService.deleteCustomerByEmail(email);
        return "Delete the customer successfully! email: "+email;
    }



}

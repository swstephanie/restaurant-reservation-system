package com.sw.restaurant.controller;

import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public Customer createNewCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }


    @RequestMapping(value = "customer/update",method =RequestMethod.PUT)
    public String updateCustomerInfo(@RequestBody Customer customer) throws IllegalAccessException {
        if (customer.getEmail()==null) return "Please provide email address of the customer";
        customerService.updateCustomerInfo(customer);
        return "Update customer information successfully! email: " + customer.getEmail();
    }

    @RequestMapping(value = "customer/delete",method = RequestMethod.DELETE)
    public String deleteCustomerByEmail(@RequestParam(value = "email") String email) {
        customerService.deleteCustomerByEmail(email);
        return "Delete the customer successfully! email: "+email;
    }



}

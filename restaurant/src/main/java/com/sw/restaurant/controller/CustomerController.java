package com.sw.restaurant.controller;

import com.sw.restaurant.pojo.Customer;
import com.sw.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping(value = "/")
    public String index() {
        String src = "https://i.chzbgr.com/full/9195088128/hBCF70164/programmer-meme-white-debugging-t-monkeyusercom";
        return String.format("<h1>Hey! Friend!</h1> <h2>Enjoy this system! </h2> <ul><li><a href=\"/dashboard\">Profile</a></li><li><a href=\"/reservation/new\">New Reservation</a></li><li><a>Happy</a></li></ul><img src=\"%s\" >",src);
    }

    //Page for Administrator
    @RequestMapping(value = "/admin/hello",method = RequestMethod.GET)
    public String adminHello(){
        return "<h1>Hello, Admin!</h1>";
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
    @RequestMapping(value = "customer/signup",method = RequestMethod.POST)
    public Customer createNewCustomer(@RequestBody Customer customer) throws IllegalAccessException {
        return customerService.createCustomer(customer);
    }
//    @RequestMapping(value = "customer/signup",method = RequestMethod.POST)
//    public String createNewCustomer(@RequestBody Customer customer) throws IllegalAccessException {
//        return "YESSSS";
//    }

//    @RequestMapping(value = "customer/update",method =RequestMethod.PUT)
//    public String updateCustomerInfo(@RequestBody Customer customer) throws IllegalAccessException {
//        if (customer.getEmail()==null) return "Please provide email address of the customer";
//        customerService.updateCustomerInfo(customer);
//        return "Update customer information successfully! email: " + customer.getEmail();
//    }
    @RequestMapping(value = "customer/update",method =RequestMethod.PUT)
    public String updateCustomerInfo(HttpServletRequest request, Principal principal) throws IllegalAccessException, ParseException {
        Customer customer = new Customer();
        String customer_email = principal.getName();
        customer.setEmail(customer_email);
        if (customer_email==null) {
            return "Please provide email address of the customer";
        }
        customer.setCustomerName(request.getParameter("customer_name"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        customer.setDob(format.parse(request.getParameter("dob")));
        customer.setCellphone(request.getParameter("cellphone"));

        customerService.updateCustomerInfo(customer);
        return "Update customer information successfully! email: " + customer.getEmail();
    }



    @RequestMapping(value = "customer/delete",method = RequestMethod.DELETE)
    public String deleteCustomerByEmail(@RequestParam(value = "email") String email) {
        customerService.deleteCustomerByEmail(email);
        return "Delete the customer successfully! email: "+email;
    }



}

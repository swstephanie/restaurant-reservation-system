package com.sw.restaurant.service;

import com.sw.restaurant.pojo.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerServiceInterface {

    //Customer createCustomer(String customerName, String email, Date dob, String gender, String cellphone);

     Customer createCustomer(Customer customer);

    void deleteCustomerByEmail(String email);
    Customer updateCustomerName(String email, String customerName);
    Customer updateCustomerDob(String email, Date dob);
    Customer updateCustomerGender(String email, String gender);
    Customer updateCustomerPoints(String email, int points);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    List<Customer> getCustomerByName(String name);


}

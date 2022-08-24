package com.sw.restaurant.service;

import com.sw.restaurant.dao.CustomerRepository;
import com.sw.restaurant.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    //public Customer createCustomer(String customerName, String email, Date dob, String gender, String cellphone) {
    public Customer createCustomer(Customer customer) {
        String email = customer.getEmail();
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(!optionalCustomer.isPresent())
            throw new RuntimeException("Create customer failed! The customer email has already existed. email: " + email);
        Customer customer1 = new Customer();
        customer1.setEmail(email);
        customer1.setDob(customer.getDob());
        customer1.setGender(customer.getGender());
        customer1.setCustomerName(customer.getCustomerName());
        customer1.setPoints(0);
        customer1.setCellphone(customer.getCellphone());
        customerRepository.save(customer1);
        return customer1;
    }

    @Override
    public void deleteCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()) customerRepository.deleteByEmail(email);
        else throw new RuntimeException("Delete customer failed because email address does not exist! email: "+email);

    }

    @Override
    public Customer updateCustomerName(String email, String customerName) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerName);
            customerRepository.save(customer);
            return customer;
        } else throw new RuntimeException("Update customer name failed because email address does not exist! email: "+email);
    }

    @Override
    public Customer updateCustomerDob(String email, Date dob) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setDob(dob);
            customerRepository.save(customer);
            return customer;
        } else throw new RuntimeException("Update customer dob failed because email address does not exist! email: "+email);

    }

    @Override
    public Customer updateCustomerGender(String email, String gender) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setGender(gender);
            customerRepository.save(customer);
            return customer;
        } else throw new RuntimeException("Update customer gender failed because email address does not exist! email: "+email);

    }

    @Override
    public Customer updateCustomerPoints(String email, int points) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setPoints(points);
            customerRepository.save(customer);
            return customer;
        } else throw new RuntimeException("Update customer dob failed because email address does not exist! email: "+email);

    }
    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = customerRepository.findAll();
        if (!customerList.isEmpty()){
            return customerList;
        } else throw new RuntimeException("Get customers failed. No customers in the database.");
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        } else throw new RuntimeException("Get customer failed. Customer not found! email: " + email);
    }


    @Override
    public List<Customer> getCustomerByName(String name) {
        List<Customer> customerList = customerRepository.findByCustomerName(name);
        if (!customerList.isEmpty()){
            return customerList;
        } else throw new RuntimeException("Get customers failed. Customer not found! name: "+name);

    }
}

package com.sw.restaurant.service;

import com.mysql.cj.log.Log;
import com.sw.restaurant.dao.CustomerRepository;
import com.sw.restaurant.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Cacheable(cacheNames = "customer",key = "#result.id")
    public Customer createCustomer(Customer customer) throws IllegalAccessException {
        Field[] fields = customer.getClass().getDeclaredFields();
        for(Field field: fields) {
            field.setAccessible(true);
            if((!field.getName().equalsIgnoreCase("id")) && (!field.getName().equalsIgnoreCase("id")) && field.get(customer) == null)
                throw new RuntimeException("Create customer failed! Please provide more info about the new customer.");
        }

        String email = customer.getEmail();
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent())
            throw new RuntimeException("Create customer failed! The customer email has already existed. email: " + email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode(customer.getPassword());
        customer.setPassword(pw);
        customer.setId();
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomerByEmail(String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerRepository.deleteById(customer.getId());
        }
        else throw new RuntimeException("Delete customer failed because email address does not exist! email: "+email);

    }

    @Override
    public Customer updateCustomerInfo(Customer customer) throws IllegalAccessException{
        if (customer.getEmail()==null) throw new RuntimeException("Please provide customer email.");
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
        if(optionalCustomer.isPresent()){
            Customer old = optionalCustomer.get();
            Field[] fields = customer.getClass().getDeclaredFields();
            for(Field field: fields) {
                field.setAccessible(true);
                if(field.get(customer)!=null && (!field.getName().equalsIgnoreCase("id"))) {
                    field.set(old,field.get(customer));
                }
            }
            customerRepository.save(old);
            return old;

        } else throw new RuntimeException("Update customer name failed because email address does not exist! email: "+ customer.getEmail());

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

    @Override
    public Page<Customer> getAllCustomerPagination(int page, int size) {
        Sort sort = Sort.by("email");
        sort.descending();
        return customerRepository.findAll(PageRequest.of(page,size,sort));
    }
    @Override
    public Page<Customer> getAllByCustomerNamePagination(String name, int page, int size) {
        return customerRepository.getAllByCustomerName(name, PageRequest.of(page,size));
    }
}

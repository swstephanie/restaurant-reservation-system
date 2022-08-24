package com.sw.restaurant.dao;

import com.sw.restaurant.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);
    List<Customer> findByCustomerName(String customerName);
    void deleteByEmail(String email);


}

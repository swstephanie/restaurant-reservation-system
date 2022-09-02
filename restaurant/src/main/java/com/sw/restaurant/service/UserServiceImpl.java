package com.sw.restaurant.service;

import com.sw.restaurant.dao.CustomerRepository;
import com.sw.restaurant.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(username);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<GrantedAuthority> roleList = AuthorityUtils.commaSeparatedStringToAuthorityList("user,admin");
            User user = new User(customer.getEmail(),customer.getPassword(),roleList);
            return user;

        } else throw new UsernameNotFoundException("User not found! username: "+username);

    }

}

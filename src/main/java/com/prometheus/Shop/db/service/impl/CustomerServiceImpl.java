package com.prometheus.Shop.db.service.impl;

import com.prometheus.Shop.db.repository.CustomerRepository;
import com.prometheus.Shop.db.service.api.CustomerService;
import com.prometheus.Shop.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> getCustomer() {
        return customerRepository.getAll();
    }

    @Override
    public Customer get(int id) {
        return customerRepository.get(id);
    }

    @Override
    public Integer add(Customer customer) {
        return customerRepository.add(customer);
    }
}

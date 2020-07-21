package com.prometheus.Shop.db.service.api;

import com.prometheus.Shop.domain.Customer;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(); // vrati vsetkych customerov

    @Nullable
    Customer get(int id); // vrati customera na zaklade id

    @Nullable
    Integer add(Customer customer); // vracia generovane id

}

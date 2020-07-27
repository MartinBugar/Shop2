package com.prometheus.Shop.db.service.impl;

import com.prometheus.Shop.db.repository.CustomerAccountRepository;
import com.prometheus.Shop.db.service.api.CustomerAccountService;
import com.prometheus.Shop.db.service.api.CustomerService;
import com.prometheus.Shop.domain.Customer;
import com.prometheus.Shop.domain.CustomerAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private  final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public void addCustomerAccount(CustomerAccount customerAccount) {
        customerAccountRepository.add(customerAccount);
    }

    @Override
    public double getMoney(int customerId) {
        return customerAccountRepository.getMoney(customerId);
    }

    @Override
    public void setMoney(int customerId, double money) {
        customerAccountRepository.setMoney(customerId,money);
    }
}

package com.prometheus.Shop.db.service.api;

import com.prometheus.Shop.domain.CustomerAccount;
import org.springframework.lang.Nullable;

public interface CustomerAccountService {
     void addCustomerAccount (CustomerAccount customerAccount);

     @Nullable
     double getMoney (int customerId);

     void setMoney(int customerId, double money);
}

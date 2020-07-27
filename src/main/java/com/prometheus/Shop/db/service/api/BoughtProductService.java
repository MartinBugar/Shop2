package com.prometheus.Shop.db.service.api;

import com.prometheus.Shop.domain.BoughtProduct;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoughtProductService {
    void add(BoughtProduct boughtProduct);

    List<BoughtProduct> getAllByCustomerId(int customerId);
}

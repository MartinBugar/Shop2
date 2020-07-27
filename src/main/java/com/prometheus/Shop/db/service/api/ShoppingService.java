package com.prometheus.Shop.db.service.api;

import com.prometheus.Shop.db.service.api.request.BuyProductRequest;
import com.prometheus.Shop.db.service.api.response.BuyProductResponse;

public interface ShoppingService {
    BuyProductResponse buyProduct(BuyProductRequest request);
}

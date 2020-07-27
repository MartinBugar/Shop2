package com.prometheus.Shop.db.service.api;

import com.prometheus.Shop.db.service.api.request.UpdateProductRequest;
import com.prometheus.Shop.domain.Product;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    @Nullable
    Product get (int id);

    @Nullable
    Integer add(Product product); // vracia vygenerovane id

    void delete (int id);


    void update (int id, UpdateProductRequest request);

    void updateAvailableInternal (int id, int newAvailable);
}

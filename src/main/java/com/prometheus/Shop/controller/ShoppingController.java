package com.prometheus.Shop.controller;


import com.prometheus.Shop.db.service.api.ShoppingService;
import com.prometheus.Shop.db.service.api.request.BuyProductRequest;
import com.prometheus.Shop.db.service.api.response.BuyProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("buy")
    public ResponseEntity buy (@RequestBody BuyProductRequest buyProductRequest){
    BuyProductResponse buyProductResponse = shoppingService.buyProduct(buyProductRequest);

    if (buyProductResponse.isSuccess() ){
        return ResponseEntity.ok().build();
    } else {
        return new ResponseEntity<>(buyProductResponse.getErrorMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    }
}

package com.prometheus.Shop.controller;


import com.prometheus.Shop.db.service.api.MerchantService;
import com.prometheus.Shop.domain.Merchant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity add (@RequestBody Merchant merchant){
        Integer id = merchantService.add(merchant);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        else {
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @GetMapping("{id}")
        public ResponseEntity get (@PathVariable ("id") int id){

        Merchant merchant = merchantService.get(id);

        if (merchant != null){
            return new ResponseEntity <>(merchant,HttpStatus.OK);
        } else {
            return new ResponseEntity <>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity getAll (){
        List<Merchant> merchantList = merchantService.getMerchants();
        return new ResponseEntity<>(merchantList,HttpStatus.OK);
    }

}

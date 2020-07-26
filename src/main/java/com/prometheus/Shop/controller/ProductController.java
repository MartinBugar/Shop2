package com.prometheus.Shop.controller;

import com.prometheus.Shop.db.service.api.ProductService;
import com.prometheus.Shop.db.service.api.request.UpdateProductRequest;
import com.prometheus.Shop.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity addProduct (@RequestBody Product product) {
        Integer id = productService.add(product);

        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

        @GetMapping()
        public ResponseEntity getAll (){
            List<Product> productList = productService.getProducts();
            return  new ResponseEntity<>(productList, HttpStatus.OK);
        }

        @GetMapping("{id}")
        public ResponseEntity getProduct (@PathVariable("id") int id){
        Product product = productService.get(id);

        if (product != null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        }

        @PatchMapping("{id}")
        public ResponseEntity updateProduct (@PathVariable ("id") int id, @RequestBody UpdateProductRequest updateProductRequest){
            if (productService.get(id) != null){
                productService.update(id,updateProductRequest);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity
                        .status(HttpStatus.PRECONDITION_FAILED)
                        .body("Product with id: " + id + " does not exist ");
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity deleteProduct (@PathVariable("id") int id){
        if (productService.get(id) != null){
            productService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Product with id: " + id + " does not exist ");
        }
        }



}

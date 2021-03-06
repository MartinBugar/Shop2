package com.prometheus.Shop;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prometheus.Shop.db.service.api.request.UpdateProductRequest;
import com.prometheus.Shop.domain.Customer;
import com.prometheus.Shop.domain.Merchant;
import com.prometheus.Shop.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Merchant merchant;

    @Before
    public void createMerchant () throws Exception {
        if (merchant == null){
             merchant = new Merchant("name1","email1","adresa1");

            String id = mockMvc.perform(post("/merchant")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(merchant)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            merchant.setId(objectMapper.readValue(id, Integer.class));
        }

    }




    @Test
    public void customerTests  () throws Exception{
        Customer customer = new Customer("Ferko", "Mrkvicka","martin@martin.com","vansovej15",26,"030252252");
//add customer test
       String id =  mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

       customer.setId(objectMapper.readValue(id,Integer.class));

       System.out.println(id);

       //get customer test
        String customerJson = mockMvc.perform(get("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(customerJson, Customer.class);
        Assert.assertEquals(customer,returnedCustomer);

        //get all customers
        String customersJsonList = mockMvc.perform(get("/customer")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(customersJsonList);

        List <Customer> customers = objectMapper.readValue(customersJsonList, new TypeReference<List<Customer>>(){});

        assert customers.size() == 1;
        Assert.assertEquals(customer,customers.get(0));
    }

    @Test
    public void merchantTests () throws Exception {
        //Add merchant

        /*
        Merchant merchant = new Merchant("name1","email1","adresa1");

        String id = mockMvc.perform(post("/merchant")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(merchant)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        merchant.setId(objectMapper.readValue(id, Integer.class));
        */

        //merchant is already created

        //Get merchant
        String merchantJson = mockMvc.perform(get("/merchant/" + merchant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Merchant returnedMerchant = objectMapper.readValue(merchantJson, Merchant.class);
        Assert.assertEquals(merchant,returnedMerchant);


        //Get all merchants
        String merchantsJsonLists = mockMvc.perform(get("/merchant")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(merchantsJsonLists);
        List <Merchant> merchants = objectMapper.readValue(merchantsJsonLists, new TypeReference<List<Merchant>>() {});
        assert merchants.size() == 1;
        Assert.assertEquals(merchant,merchants.get(0));
    }

    @Test
    public void productTests () throws  Exception {
        Product product = new Product(merchant.getId(),"klavesnica","super_klavesnica",55.9,250);

        //Add product
        String id = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        product.setId(objectMapper.readValue(id, Integer.class));

        //Get product
        String returnedProduct = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Product productJson = objectMapper.readValue(returnedProduct,Product.class);
        Assert.assertEquals(product,productJson);

        //Get all product
        String productsJsonList = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(productsJsonList);

        List <Product> products = objectMapper.readValue(productsJsonList, new TypeReference<List<Product>>() {});
        assert products.size() == 1;

        Assert.assertEquals(product,products.get(0));

        //update product
        double updatePrice = product.getPrice() + 1;
        int updateAvailable  = product.getAvailable() + 5;
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(),product.getDescription(),updatePrice,updateAvailable);

        mockMvc.perform(patch("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProductRequest)))
                .andExpect(status().isOk());

        String returnedUpdatedProduct = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Product updatedProduct = objectMapper.readValue(returnedUpdatedProduct, Product.class);
        assert updatePrice == updatedProduct.getPrice();
        assert updateAvailable == updatedProduct.getAvailable();
        System.out.println(updatedProduct);


        //Delete product
        mockMvc.perform(delete("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        String productsJsonList2 = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List <Product> products2 = objectMapper.readValue(productsJsonList2, new TypeReference<List<Product>>() {});
        assert products2.size() == 0;

    }


}

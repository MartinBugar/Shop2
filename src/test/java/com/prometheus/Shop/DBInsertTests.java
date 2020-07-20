package com.prometheus.Shop;

import com.prometheus.Shop.domain.Customer;
import com.prometheus.Shop.domain.Merchant;
import com.prometheus.Shop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBInsertTests {

    private final String insertCustomer = "INSERT INTO customer (name, surname, email, address, age, phone_number) values (?, ?, ?, ?, ?, ?)";
    private final String insertMerchant = "INSERT INTO merchant (name, email, address) values (?, ?, ?)";
    private final String insertProduct = "INSERT INTO product (merchant_id, name, description, price, created_at, available) values (?, ?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createCustomer(){
        Customer customer = new Customer();
        customer.setName("Ferko");
        customer.setSurname("Mrkvicka");
        customer.setEmail("ferko@gmail.com");
        customer.setAddress("T.Saratov");
        customer.setAge(22);
        customer.setPhoneNumber("0202 363 666");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertCustomer);
                ps.setString(1,customer.getName());
                ps.setString(2,customer.getSurname());
                ps.setString(3,customer.getEmail());
                ps.setString(4,customer.getAddress());
                ps.setInt(5,customer.getAge());
                ps.setString(6,customer.getPhoneNumber());
                return ps;
            }
        });
    }



    @Test
    public void createMerchant(){
        Merchant merchant = new Merchant();
        merchant.setName("merchant1");
        merchant.setEmail("merchant.email");
        merchant.setAddress("merchantova 15");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertMerchant);
                ps.setString(1,merchant.getName());
                ps.setString(2,merchant.getEmail());
                ps.setString(3,merchant.getAddress());
                return ps;
            }
        });
    }

    @Test
    public void createProduct(){
        Product product = new Product();
        product.setMerchant_id(1);
        product.setName("product1");
        product.setDescription("indulona to je");
        product.setPrice(15.5);
        product.setCreatedAt(Timestamp.from(Instant.now()));
        product.setAvailable(10);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertProduct);
                ps.setInt(1,product.getMerchant_id());
                ps.setString(2,product.getName());
                ps.setString(3,product.getDescription());
                ps.setDouble(4,product.getPrice());
                ps.setTimestamp(5,product.getCreatedAt());
                ps.setInt(6,product.getAvailable());
                return ps;
            }
        });
    }


}

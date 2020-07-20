package com.prometheus.Shop.db.repository;

import com.prometheus.Shop.db.mapper.ProductRowMapper;
import com.prometheus.Shop.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository  {

    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    public ProductRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(int id){
        final String sql = "select * from product where product.id = " + id;
        try{
            return jdbcTemplate.queryForObject(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // dokoncit toto


}

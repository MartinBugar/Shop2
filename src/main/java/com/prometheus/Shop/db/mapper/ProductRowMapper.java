package com.prometheus.Shop.db.mapper;

import com.prometheus.Shop.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper <Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setMerchant_id(resultSet.getInt("merchant_id"));
        product.setDescription(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));
        product.setAvailable(resultSet.getInt("available"));

        return product;
    }
}

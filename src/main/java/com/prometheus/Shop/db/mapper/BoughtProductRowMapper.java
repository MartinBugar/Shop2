package com.prometheus.Shop.db.mapper;

import com.prometheus.Shop.domain.BoughtProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoughtProductRowMapper implements RowMapper <BoughtProduct> {
    @Override
    public BoughtProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        BoughtProduct boughtProduct = new BoughtProduct();
        boughtProduct.setProductId(resultSet.getInt("product_id"));
        boughtProduct.setCustomerId(resultSet.getInt("customer_id"));
        boughtProduct.setQuantity(resultSet.getInt("quantity"));
        boughtProduct.setBoughtAt(resultSet.getTimestamp("bought_at"));
        return  boughtProduct;
    }
}

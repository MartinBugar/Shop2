package com.prometheus.Shop.db.mapper;

import com.prometheus.Shop.domain.Merchant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MerchantRowMapper implements RowMapper <Merchant> {
    @Override
    public Merchant mapRow(ResultSet resultSet, int i) throws SQLException {
       Merchant merchant = new Merchant();
       merchant.setId(resultSet.getInt("id"));
       merchant.setName(resultSet.getString("name"));
       merchant.setEmail(resultSet.getString("email"));
       merchant.setAddress(resultSet.getString("address"));
       return merchant;
    }
}

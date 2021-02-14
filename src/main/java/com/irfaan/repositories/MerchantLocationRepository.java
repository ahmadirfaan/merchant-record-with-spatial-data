package com.irfaan.repositories;

import com.irfaan.entities.MerchantLocation;

import com.irfaan.models.MerchantLocationDistance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MerchantLocationRepository {

    private final JdbcTemplate jdbcTemplate;

    public MerchantLocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MerchantLocation> findAllMerchantLocationWithInSubCounty(Integer subCountyId) {
        String sql = "SELECT m.id, m.name, ST_X(m.geom) AS x, ST_Y(m.geom ) AS y FROM merchant_location m, " +
                "nairobi_sub_counties nsc WHERE ST_Within(m.geom, nsc.geom) AND nsc.id= ?";
        return jdbcTemplate.query(sql, new Object[]{subCountyId}, (rs, rowNum) -> {
            MerchantLocation merchant = new MerchantLocation();
            merchant.setId(rs.getInt(1));
            merchant.setName(rs.getString(2));
            merchant.setCoordinateX(rs.getDouble(3));
            merchant.setCoordinateY(rs.getDouble(4));
            return merchant;
        });
    }

    public List<MerchantLocationDistance> findAllMerchanyByDistanceFromCustomer(Double customerLongitude, Double customerLattitude) {
        String sql = "SELECT m.id, m.name, ST_X(m.geom) AS x, ST_Y(m.geom ) AS y, ST_DISTANCE(m.geom, ST_SetSRID(ST_Point(?, ?), 4326))" +
                "AS distance FROM merchant_location m ORDER BY m.geom <-> ST_SetSRID(ST_Point(?, ?), 4326) LIMIT 5";
        Object[] param = {customerLongitude, customerLattitude, customerLongitude, customerLattitude};
        return jdbcTemplate.query(sql, param ,(rs, rowNum) ->{
            MerchantLocationDistance merchant = new MerchantLocationDistance();
            merchant.setId(rs.getInt(1));
            merchant.setName(rs.getString(2));
            merchant.setCoordinateX(rs.getDouble(3));
            merchant.setCoordinateY(rs.getDouble(4));
            merchant.setDistance(rs.getDouble(5));
            return merchant;
        });
    }

}

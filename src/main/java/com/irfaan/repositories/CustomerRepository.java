package com.irfaan.repositories;

import com.irfaan.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomerRepository implements CommonRepository<Customer, Integer> {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Customer
     */
    RowMapper<Customer> rowMapper = (rs, rowNum) -> {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
        customer.setModifiedDate(rs.getObject("modified_date", LocalDateTime.class));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        return customer;
    };

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT id, created_date, modified_date, first_name, last_name, address, city FROM customer";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customer(created_date, first_name, last_name, address, city) VALUES(NOW(), ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getCity());
        System.out.println(insert);
        if(insert == 1) {
            log.info("New Customer Has Been Saved : " + customer.getFirstName() + " " + customer.getLastName());
            return customer;
        } else {
            log.info("Failed to Saved : ");
            return null;
        }
    }

    @Override
    public Customer findById(Integer id) {
        String sql = "SELECT id, created_date, modified_date, first_name, last_name, address, city FROM customer where id = ?";
        Customer customer = null;
        try {
            customer = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            log.info("Customer not found : " + id);
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        String sql = "UPDATE customer set modified_date = NOW(), first_name = ?, last_name = ?, address = ?, city = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(),
                customer.getAddress(), customer.getCity(), id);
        if(update == 1) {
            log.info("Customer Updated For ID : " + id);
            return customer;
        } else {
            log.info("Failed to Updated : ");
            return null;
        }
    }

    @Override
    public Customer removeByID(Integer id) {
        String sql = "DELETE FROM customer where id = ?";
        Customer customer = findById(id);
        int removed = jdbcTemplate.update(sql, id);
        if(removed == 1) {
            log.info("Customer deleted : " + id);
        } else {
            log.info("Failed to deleted : ");
        }
        return customer;
    }

}

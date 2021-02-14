package com.irfaan.repositories;

import com.irfaan.entities.RecordsTransaction;
import com.irfaan.models.RecordCustomerMerchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RecordTransactionRepository implements CommonRepository<RecordsTransaction, Integer> {
    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public RecordTransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Customer
     */
    RowMapper<RecordsTransaction> rowMapper = (rs, rowNum) -> {
        RecordsTransaction record = new RecordsTransaction();
        record.setId(rs.getInt("id"));
        record.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
        record.setModifiedDate(rs.getObject("modified_date", LocalDateTime.class));
        record.setCustomerId(rs.getInt("customer_id"));
        record.setMerchantId(rs.getInt("merchant_id"));
        return record;
    };

    @Override
    public List<RecordsTransaction> findAll() {
        String sql = "SELECT id, created_date, modified_date, customer_id, merchant_id FROM record_transaction";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public RecordsTransaction save(RecordsTransaction record) {
        String sql = "INSERT INTO record_transaction(created_date, customer_id, merchant_id) VALUES(NOW(), ?, ?)";
        int insert = jdbcTemplate.update(sql, record.getCustomerId(), record.getMerchantId());
        System.out.println("INSERT ADALAH : " + insert);
        if (insert == 1) {
            log.info("New Record Transaction Has Been Saved in Merchant Id : " + record.getMerchantId());
            return record;
        } else {
            log.info("Failed to Saved : ");
            return null;
        }
    }

    @Override
    public RecordsTransaction findById(Integer id) {
        String sql = "SELECT id, created_date, modified_date, customer_id, merchant_id FROM record_transaction where id = ?";
        RecordsTransaction record = null;
        try {
            record = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException e) {
            log.info("Record not found in ID : " + id);
        }
        return record;
    }

    @Override
    public RecordsTransaction update(RecordsTransaction record, Integer id) {
        String sql = "UPDATE record_transaction set modified_date = NOW(), customer_id = ?, merchant_id = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, record.getCustomerId(), record.getMerchantId(), id);
        if (update == 1) {
            log.info("Record Updated For ID : " + id);
            return record;
        } else {
            log.info("Failed to Updated : ");
            return null;
        }
    }

    @Override
    public RecordsTransaction removeByID(Integer id) {
        String sql = "DELETE FROM record_transaction where id = ?";
        RecordsTransaction record = findById(id);
        int removed = jdbcTemplate.update(sql, id);
        if (removed == 1) {
            log.info("Record deleted : " + id);
        } else {
            log.info("Failed to deleted : ");
        }
        return record;
    }

    public List<RecordCustomerMerchant> findAllRecordWithCustomerAndMerchant() {
        String sql = "SELECT r.id, c.first_name, c.last_name, m.name, r.created_date, r.modified_date " +
                "FROM record_transaction r INNER JOIN customer c ON r.customer_id = c.id " +
                "INNER JOIN merchant_location m ON r.merchant_id = m.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RecordCustomerMerchant record = new RecordCustomerMerchant();
            record.setId(rs.getInt(1));
            record.setUserFirstName(rs.getString(2));
            record.setUserLastName(rs.getString(3));
            record.setMerchantName(rs.getString(4));
            record.setCreatedDate(rs.getObject("created_date", LocalDateTime.class));
            record.setModifiedDate(rs.getObject("modified_date", LocalDateTime.class));
            return record;
        });
    }
}

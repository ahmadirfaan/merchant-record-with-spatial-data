package com.irfaan.services.impl;

import com.irfaan.entities.RecordsTransaction;
import com.irfaan.models.RecordCustomerMerchant;
import com.irfaan.repositories.RecordTransactionRepository;
import com.irfaan.services.RecordTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordTransactionServiceImpl implements RecordTransactionService {

    @Autowired
    private RecordTransactionRepository repository;

    @Override
    public List<RecordsTransaction> findAll() {
        return repository.findAll();
    }

    @Override
    public RecordsTransaction save(RecordsTransaction record) {
        RecordsTransaction entity = null;
        if(record != null) {
            entity = repository.save(record);
        }
        return entity;
    }

    @Override
    public RecordsTransaction findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public RecordsTransaction update(RecordsTransaction record, Integer id) {
        RecordsTransaction entity = findById(id);
        if(entity != null) {
            if(record.getMerchantId() == null) {
                record.setMerchantId(entity.getMerchantId());
            }
            if(record.getCustomerId() == null) {
                record.setCustomerId(entity.getCustomerId());
            }
            entity = repository.update(record, id);
        }
        return entity;
    }

    @Override
    public RecordsTransaction removeByID(Integer id) {
        RecordsTransaction entity = findById(id);
        if(entity != null) {
            entity = repository.removeByID(id);
        }
        return entity;
    }

    @Override
    public List<RecordCustomerMerchant> findAllRecordWithCustomerAndMerchant() {
        return repository.findAllRecordWithCustomerAndMerchant();
    }
}

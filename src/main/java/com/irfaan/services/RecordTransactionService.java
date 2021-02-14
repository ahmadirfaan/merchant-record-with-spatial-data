package com.irfaan.services;

import com.irfaan.entities.RecordsTransaction;
import com.irfaan.models.RecordCustomerMerchant;

import java.util.List;

public interface RecordTransactionService extends CommonService<RecordsTransaction, Integer> {

    List<RecordCustomerMerchant> findAllRecordWithCustomerAndMerchant();
}

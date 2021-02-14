package com.irfaan.entities;

import java.time.LocalDateTime;

public class RecordsTransaction extends AbstractEntity<Integer> {

    private Integer id;
    private Integer customerId;
    private Integer merchantId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "RecordsTransaction{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", merchantId=" + merchantId +
                '}';
    }
}

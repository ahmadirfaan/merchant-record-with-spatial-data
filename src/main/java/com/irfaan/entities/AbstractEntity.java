package com.irfaan.entities;

import java.time.LocalDateTime;

public abstract class AbstractEntity<ID> {
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public abstract ID getId();

    public abstract void setId(ID id);

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


}

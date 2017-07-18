package com.javangarda.fantacalcio.commons.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
public class VersionedDefaultEntity<T> extends DefaultEntity<T> {

    @Version
    private Long version;

    private Instant createdDateTime;

    private Instant updatedDateTime;

    public VersionedDefaultEntity() {
        super();
    }

    public VersionedDefaultEntity(T id){
        super(id);
    }

    @PrePersist
    public void updateCreatedDateTime(){
        this.createdDateTime=Instant.now();
    }

    @PreUpdate
    public void refreshModificationDateTime(){
        this.updatedDateTime=Instant.now();
    }
}

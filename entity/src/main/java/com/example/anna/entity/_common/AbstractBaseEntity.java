package com.example.anna.entity._common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 엔티티 자동 생성자,일시 / 수정자,일시
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Access(AccessType.FIELD)
@Getter
public abstract class AbstractBaseEntity implements Serializable {

    @CreatedDate
    @Column(name = "CRE_DATE", updatable = false)
    protected LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "MOD_DATE", updatable = true)
    protected LocalDateTime lastModifiedDateTime;

    @Column(name = "CRE_ID", updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(name = "MOD_ID", updatable = true)
    @LastModifiedBy
    protected String lastModifiedBy;
}

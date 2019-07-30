package com.yc.intelligence.dishwasher.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 4506594388463796269L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "create_time",nullable = false,updatable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "update_time",nullable = false)
    private LocalDateTime updateTime = LocalDateTime.now();

    @PrePersist
    protected void onPrePersist() {
        this.updateTime = createTime;
    }

    @PreUpdate
    protected void onPreUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}

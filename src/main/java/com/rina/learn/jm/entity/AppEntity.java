/*
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 */

package com.rina.learn.jm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

import lombok.*;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.rina.learn.jm.jpa.MultiTenantJpaTransactionManager;

@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "TENANT_ID", discriminatorType = DiscriminatorType.STRING,
        contextProperty = MultiTenantJpaTransactionManager.TENANT_DESCRIMINATOR_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app")
@Cacheable(value=false)
public class AppEntity {
    @Id
    @GeneratedValue(generator = "APP_ID")
    @UuidGenerator(name = "APP_ID")
    @Column(name = "id", unique=true, nullable=false, length=200)
    private String id;

    @Column(name = "app_a", nullable=false, length=200)
    private String appA;

    @Column(name = "app_b", nullable=false)
    private String appB;

    @Version
    @Column(name = "version", nullable=false)
    private Integer version;

    @Column(name = "app_c", nullable=false)
    private Boolean appC;

    @Column(name = "app_d", length=5000)
    private String appD;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "created_at", nullable=false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "changed_at", nullable=false)
    private Date changedAt;

    @OneToMany(targetEntity = BppEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    @Valid
    private List<BppEntity> bpps;

    @OneToMany(targetEntity = CppEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "app_id", referencedColumnName = "id")
    @Valid
    private List<CppEntity> cpps;

    @OneToOne(targetEntity = DppEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @PrimaryKeyJoinColumn
    @Valid
    private DppEntity dpp;
}

/*
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 */

package com.rina.learn.jm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rina.learn.jm.jpa.MultiTenantJpaTransactionManager;
import lombok.*;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;

@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "TENANT_ID", contextProperty = MultiTenantJpaTransactionManager.TENANT_DESCRIMINATOR_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fpp")
public class FppEntity {
    @Id
    @GeneratedValue(generator = "FPP_ID")
    @UuidGenerator(name = "FPP_ID")
    @Column(name = "id", unique=true, nullable=false, length=200)
    private String id;

    @Column(name = "app_id", nullable=false, length=200)
    private String appId;

    @Column(name = "fpp_a", nullable=false, length=50)
    private String fppA;
}

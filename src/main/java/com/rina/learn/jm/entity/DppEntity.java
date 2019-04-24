/*
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 */

package com.rina.learn.jm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

import com.rina.learn.jm.jpa.MultiTenantJpaTransactionManager;

import lombok.*;

@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "TENANT_ID", discriminatorType = DiscriminatorType.STRING,
        contextProperty = MultiTenantJpaTransactionManager.TENANT_DESCRIMINATOR_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dpp")
public class DppEntity {
    @Id
    @Column(name = "app_id", unique=true, nullable=false, length=200)
    private String appId;

    @Column(name = "dpp_a")
    private String dppA;

    @Column(name = "dpp_b", length=10)
    private String dppB;

    @Column(name = "dpp_c")
    private Integer dppC;

    @Column(name = "dpp_d")
    private Boolean dppD;

    @Column(name = "dpp_e", nullable=false)
    private Boolean dppE;

    @OneToMany(targetEntity = EppEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    @Valid
    private List<EppEntity> epps;

    @OneToMany(targetEntity = FppEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    @Valid
    private List<FppEntity> fpps;
}

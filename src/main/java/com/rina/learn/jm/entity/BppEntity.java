package com.rina.learn.jm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.UuidGenerator;

import com.rina.learn.jm.jpa.MultiTenantJpaTransactionManager;

@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "TENANT_ID", contextProperty = MultiTenantJpaTransactionManager.TENANT_DESCRIMINATOR_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bpp")
public class BppEntity {
    @Id
    @GeneratedValue(generator = "BPP_ID")
    @UuidGenerator(name = "BPP_ID")
    @Column(name = "id", unique=true, nullable=false, length=200)
    private String id;

    @Column(name = "app_id", nullable=false, length=200)
    private String appId;

    @Column(name = "bpp_a", nullable=false, length=50)
    private String bppA;

    @Column(name = "bpp_b", nullable=false)
    private String bppB;
}

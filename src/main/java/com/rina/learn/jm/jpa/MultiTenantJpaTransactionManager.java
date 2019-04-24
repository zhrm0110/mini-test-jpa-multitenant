package com.rina.learn.jm.jpa;

import javax.persistence.EntityManager;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.rina.learn.jm.util.TenantResolver;
@Slf4j
public class MultiTenantJpaTransactionManager extends JpaTransactionManager {
    private static final long serialVersionUID = 1L;
    public static final String TENANT_DESCRIMINATOR_NAME = "eclipselink.tenant-id";
    @Autowired
    private transient TenantResolver tenantResolver;
    @Override
    protected void doBegin(final Object transaction, final TransactionDefinition definition) {

         super.doBegin(transaction, definition);
        try {
            final EntityManagerHolder entityManagerHolder = (EntityManagerHolder) TransactionSynchronizationManager
                    .getResource(getEntityManagerFactory());
            final EntityManager entityManager = entityManagerHolder.getEntityManager();
            final String currentTenantSet = (String) entityManager.getProperties().get(TENANT_DESCRIMINATOR_NAME);

            String resolvedTenant = tenantResolver.getTenant();
            if (currentTenantSet != null && !currentTenantSet.equals(resolvedTenant)) {
                throw new IllegalStateException("tenant conflict");
            }
            entityManager.setProperty(TENANT_DESCRIMINATOR_NAME, resolvedTenant);

         } catch (final RuntimeException e) {
            log.info("##do cleanup to remove connection holder, due to exception thrown out");
            this.doCleanupAfterCompletion(transaction);
            throw e;
        }

     }
}

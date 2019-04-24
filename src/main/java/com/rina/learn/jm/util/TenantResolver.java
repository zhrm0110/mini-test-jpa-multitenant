package com.rina.learn.jm.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rina.learn.jm.exception.TenantNotProvidedException;
@Component
public class TenantResolver {

    public String getTenant() {

        final String tenant = getHeader("x-tenant");
        if (StringUtils.isEmpty(tenant)) {
            throw new TenantNotProvidedException("tenant not provided!!!!");
        }
        return tenant;
    }
    private String getHeader(final String header) {
        if (null == RequestContextHolder.getRequestAttributes()) {
            return null;
        }

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader(header);
    }
}

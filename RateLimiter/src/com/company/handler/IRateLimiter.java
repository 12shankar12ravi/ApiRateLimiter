package com.company.handler;

import com.company.config.RequestLimitConfig;

public interface IRateLimiter {
    boolean updateRateLimitingConfig(RequestLimitConfig requestLimitConfig);

    boolean isCallAllowed();
}

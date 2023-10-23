package com.company;

import com.company.config.RequestLimitConfig;
import com.company.handler.IRateLimiter;
import com.company.handler.RateLimiter;
import com.company.tests.RateLimiterTester;

public class Main {

    public static void main(String[] args) {
        RequestLimitConfig requestLimitConfig= new RequestLimitConfig(10,60);
        IRateLimiter rateLimiter = new RateLimiter(requestLimitConfig);

        RateLimiterTester rateLimiterTester = new RateLimiterTester(rateLimiter,requestLimitConfig);
        rateLimiterTester.runTests();
    }


}

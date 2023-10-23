package com.company.tests;

import com.company.config.RequestLimitConfig;
import com.company.handler.IRateLimiter;
import com.company.handler.RateLimiter;

public class RateLimiterTester {

    private IRateLimiter rateLimiter;
    RequestLimitConfig requestLimitConfig;

    public RateLimiterTester(IRateLimiter rateLimiter,RequestLimitConfig requestLimitConfig){
        this.rateLimiter = new RateLimiter(requestLimitConfig);
        this.requestLimitConfig = requestLimitConfig;
    }

    public void runTests(){
        testLimitBreached();
        testLimitNotBreached();
    }

    public void testLimitBreached(){
        System.out.println("Test 1 started ");
        int iterations = requestLimitConfig.getRequestLimit() + 10;
        for(int i=0 ; i<iterations ; i++){
            rateLimiter.isCallAllowed();
        }
        System.out.println("Test 1 passed");
    }

    public void testLimitNotBreached(){
        rateLimiter = new RateLimiter(requestLimitConfig);
        System.out.println("Test 2 started ");
        int iterations = requestLimitConfig.getRequestLimit() - 1;
        for(int i=0 ; i<iterations ; i++){
            rateLimiter.isCallAllowed();
        }
        System.out.println("Test 2 passed");
    }
}

package com.company.handler;

import com.company.config.RequestLimitConfig;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RateLimiter implements IRateLimiter{
    private RequestLimitConfig requestLimitConfig;
    private final Queue<Long> requestTimeStamps;

    public RateLimiter(RequestLimitConfig requestLimitConfig) {
        this.requestLimitConfig = requestLimitConfig;
        this.requestTimeStamps = new ConcurrentLinkedDeque<>();
    }

    public RequestLimitConfig getRequestLimitConfig() {
        return requestLimitConfig;
    }

    @Override
    public boolean updateRateLimitingConfig(RequestLimitConfig requestLimitConfig) {
        this.requestLimitConfig = requestLimitConfig;
        return true;
    }

    @Override
    public boolean isCallAllowed() {
        long currentTimeStamp = System.currentTimeMillis() / 1000; // 1500
        removeOlderRequestTimeStamps(currentTimeStamp); // 1561 - 1500, limit = 9
        if (requestTimeStamps.size() >= this.requestLimitConfig.getRequestLimit()) {
            System.out.println("Current Request is rate limited , time : "+currentTimeStamp);
            return false;
        }
        requestTimeStamps.add(currentTimeStamp); //  [1506,1510,1515,1520,1530,1535,1536,1545,1560]
        return true;
    }

    // older requests would be evicted
    public void removeOlderRequestTimeStamps(long currentTimeStamp) {
        while (requestTimeStamps.size() != 0 && (currentTimeStamp - requestTimeStamps.peek() > requestLimitConfig.getTime())) {
            requestTimeStamps.remove();
        }
    }


}


// No of requests in a minute
//

// noOfRequests
// firstRequestTime


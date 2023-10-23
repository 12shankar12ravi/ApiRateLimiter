package com.company.config;

public class RequestLimitConfig {
    private int requestLimit;
    private int time;

    public RequestLimitConfig(int requestLimit, int time) {
        this.requestLimit = requestLimit;
        this.time = time;
    }

    public int getRequestLimit() {
        return requestLimit;
    }

    public int getTime() {
        return time;
    }
}

package com.nkrasnovoronka.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class IpAddress {
    private final Map<String, String> ipAddresses;

    public IpAddress() {
        ipAddresses = new ConcurrentHashMap<>();
    }

    public void addIpAndUserAgent(String ip, String userAgent){
        ipAddresses.put(ip, userAgent);
    }

    public Map<String, String> getIpAddresses() {
        return ipAddresses;
    }


}

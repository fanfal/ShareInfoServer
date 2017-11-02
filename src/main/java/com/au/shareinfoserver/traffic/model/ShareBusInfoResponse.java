package com.au.shareinfoserver.traffic.model;

public class ShareBusInfoResponse {
    private String uuid;

    public ShareBusInfoResponse(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

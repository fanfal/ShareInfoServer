package com.au.shareinfoserver.model;


public enum InfoType {
    TRAFFIC_INFO(0),
    ERROR_TYPE(1000);

    int type;

    InfoType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public InfoType getType(int value) {
        switch (value) {
            case 0:
                return TRAFFIC_INFO;
            default:
                return ERROR_TYPE;
        }
    }
}

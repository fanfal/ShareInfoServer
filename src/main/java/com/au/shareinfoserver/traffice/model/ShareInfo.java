package com.au.shareinfoserver.traffice.model;

public class ShareInfo {
    private CarInfo carInfo;
    private Location location;

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

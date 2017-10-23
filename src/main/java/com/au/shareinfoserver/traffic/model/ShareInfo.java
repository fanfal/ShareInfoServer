package com.au.shareinfoserver.traffic.model;

public class ShareInfo {
    private CarInfo carInfo;
    private Location location;
    private Integer numOfPeople;
    private Boolean isAboard;

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

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public Boolean getAboard() {
        return isAboard;
    }

    public void setAboard(Boolean aboard) {
        isAboard = aboard;
    }
}

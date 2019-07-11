package com.katrenich.alex.klara.placesListScreen.model;

import java.util.Objects;

public class CoffeeShop {
    private String address;
    private String workTime;
    private String imageUrl;

    public CoffeeShop(String address, String workTime) {
        this.address = address;
        this.workTime = workTime;
    }

    public CoffeeShop(String address, String workTime, String imageUrl) {
        this.address = address;
        this.workTime = workTime;
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeShop that = (CoffeeShop) o;
        return address.equals(that.address) &&
                workTime.equals(that.workTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, workTime);
    }

    @Override
    public String toString() {
        return "CoffeeShop{" +
                "address='" + address + '\'' +
                ", workTime='" + workTime + '\'' +
                '}';
    }
}

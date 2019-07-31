package com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhonePOJO {

    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

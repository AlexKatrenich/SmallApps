package com.bistriycredit.online.nakartu.network.model.userAuthRegModule.registerPhoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneRegisterPOJO {
    
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

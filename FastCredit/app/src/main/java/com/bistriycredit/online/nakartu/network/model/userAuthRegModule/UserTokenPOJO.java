package com.bistriycredit.online.nakartu.network.model.userAuthRegModule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTokenPOJO {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;

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

    public String getToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    @Override
    public String toString() {
        return "UserTokenPOJO{" +
                "phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", rememberToken='" + rememberToken + '\'' +
                '}';
    }
}

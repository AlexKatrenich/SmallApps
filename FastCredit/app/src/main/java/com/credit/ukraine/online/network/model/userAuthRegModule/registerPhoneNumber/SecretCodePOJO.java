package com.credit.ukraine.online.network.model.userAuthRegModule.registerPhoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecretCodePOJO {

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("secret_code")
    @Expose
    private Integer secretCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(Integer secretCode) {
        this.secretCode = secretCode;
    }
}

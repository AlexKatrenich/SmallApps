package com.bistriycredit.online.nakartu.network.model.userAuthRegModule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bistriycredit.online.nakartu.network.model.BaseResponse;

public class UserTokenResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private UserTokenPOJO data;

    public UserTokenPOJO getData() {
        return data;
    }

    public void setData(UserTokenPOJO data) {
        this.data = data;
    }
}

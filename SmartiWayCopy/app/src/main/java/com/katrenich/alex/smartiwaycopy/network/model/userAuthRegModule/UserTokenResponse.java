package com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;

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

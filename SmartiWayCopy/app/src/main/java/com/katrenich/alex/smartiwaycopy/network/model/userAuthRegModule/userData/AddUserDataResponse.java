package com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;

public class AddUserDataResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private UserDataPOJO data;

    public UserDataPOJO getData() {
        return data;
    }

    public void setData(UserDataPOJO data) {
        this.data = data;
    }

}

package com.katrenich.alex.smartiwaycopy.network.model.registerPhoneNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.katrenich.alex.smartiwaycopy.network.model.BaseResponse;

public class PhoneRegisterResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private PhoneRegisterPOJO data;

    public PhoneRegisterPOJO getData() {
        return data;
    }

    public void setData(PhoneRegisterPOJO data) {
        this.data = data;
    }
}

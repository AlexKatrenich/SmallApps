package com.bistriycredit.online.nakartu.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("success")
    @Expose
    Boolean success;


    public String getMessage(){
        return message;
    }

    public Boolean getSuccess(){
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

package com.katrenich.alex.smartiwaycopy.network.model.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditPOJO {

    @SerializedName("remember_token")
    @Expose
    private String rememberToken;

    @SerializedName("value_credit")
    @Expose
    private Integer creditValue;

    @SerializedName("term_credit")
    @Expose
    private String creditReturnDate;

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Integer getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Integer creditValue) {
        this.creditValue = creditValue;
    }

    public String getCreditReturnDate() {
        return creditReturnDate;
    }

    public void setCreditReturnDate(String creditReturnDate) {
        this.creditReturnDate = creditReturnDate;
    }

    @Override
    public String toString() {
        return "CreditPOJO{" +
                "rememberToken='" + rememberToken + '\'' +
                ", creditValue=" + creditValue +
                ", creditReturnDate='" + creditReturnDate + '\'' +
                '}';
    }
}

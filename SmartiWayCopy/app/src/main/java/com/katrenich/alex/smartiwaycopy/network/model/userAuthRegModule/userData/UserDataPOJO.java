package com.katrenich.alex.smartiwaycopy.network.model.userAuthRegModule.userData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.katrenich.alex.smartiwaycopy.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataPOJO {
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("inn")
    @Expose
    private String inn;
    @SerializedName("passport")
    @Expose
    private String passport;

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public static UserDataPOJO getPOJOfromUserEntity(User user, String token) throws ParseException {
        UserDataPOJO pojo = new UserDataPOJO();
        pojo.setRememberToken(token);
        pojo.setFirstName(user.getFirstName());
        pojo.setMiddleName(user.getMiddleName());
        pojo.setLastName(user.getLastName());
        pojo.setBirthDate(transferFormatDate(user.getBirthDate()));
        pojo.setInn(user.getInnCode());
        pojo.setPassport(user.getPassportSN());

        return pojo;
    }

    private static String transferFormatDate(String date) throws ParseException {
        SimpleDateFormat currentFormat = new SimpleDateFormat("dd.mm.yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date d = currentFormat.parse(date);

        return dateFormat.format(d);
    }

    @Override
    public String toString() {
        return "UserDataPOJO{" +
                "rememberToken='" + rememberToken + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", inn='" + inn + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}

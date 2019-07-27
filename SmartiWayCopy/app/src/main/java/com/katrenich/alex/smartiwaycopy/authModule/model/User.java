package com.katrenich.alex.smartiwaycopy.authModule.model;

public class User {
    private String mFirstName;
    private String mLastName;
    private String mMiddleName;
    private String mInnCode;
    private String mPassword;
    private String mPassportSN;
    private String mMobilePhone;
    private String mBirthDate;
    private Integer mAge;

    public User(String mobilePhone) {
        mMobilePhone = mobilePhone;
    }

    public User(String phoneNumb, String pass) {
        mMobilePhone = phoneNumb;
        mPassword = pass;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(String middleName) {
        mMiddleName = middleName;
    }

    public String getInnCode() {
        return mInnCode;
    }

    public void setInnCode(String innCode) {
        mInnCode = innCode;
    }

    public String getPassportSN() {
        return mPassportSN;
    }

    public void setPassportSN(String passportSN) {
        mPassportSN = passportSN;
    }

    public String getMobilePhone() {
        return mMobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        mMobilePhone = mobilePhone;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public Integer getAge() {
        return mAge;
    }

    public void setAge(Integer age) {
        mAge = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "mMobilePhone='" + mMobilePhone + '\'' +
                '}';
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}

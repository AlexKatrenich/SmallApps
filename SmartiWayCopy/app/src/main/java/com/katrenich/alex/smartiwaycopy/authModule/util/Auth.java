package com.katrenich.alex.smartiwaycopy.authModule.util;

public interface Auth {
    boolean checkUserPhone(String phoneNumber);
    boolean checkUserVerificationCode(String code);

}

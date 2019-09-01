package com.credit.ukraine.online.creditModule.util;

import android.util.Log;

import com.credit.ukraine.online.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserInfo {
    public static final String TAG = "UserInfo";
    private static UserInfo instance = new UserInfo();
    private User currentUser = new User();

    private int creditTerm;

    private UserInfo() {

    }

    public static UserInfo getInstance() {
        return instance ;
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user){
        if(user != null){
            currentUser = user;
            Log.i(TAG, "setCurrentUser: " + currentUser);
        }
    }

    public static int calculateAgeFromBirthDay(String birth){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        int years = 0;
        int months = 0;
        int days = 0;

        try {

            Date day = sdf.parse(birth);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(day);

            int birthYear = calendar.get(Calendar.YEAR);
            int birthMonth = calendar.get(Calendar.MONTH) + 1;
            int birthDate = calendar.get(Calendar.DAY_OF_MONTH);
            int currYear = Calendar.getInstance().get(Calendar.YEAR);
            int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int currDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

            //Get difference between years
            years = currYear - birthYear;

            //Get difference between months
            months = currMonth - birthMonth;

            if (months < 0) {
                years--;
                months = 12 - birthMonth + currMonth;
                if (currDate < birthDate)
                    months--;
            } else if (months == 0 && currDate < birthDate) {
                years--;
                months = 11;
            }

            //Calculate the days
            if (currDate < birthDate){
                if (months == currMonth){
                    years--;
                }
            }

        } catch (ParseException e) {
            Log.e(TAG, "calculateAgeFromBirthDay: ", e);
        }

        return years;
    }

    public int getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(int creditTerm) {
        this.creditTerm = creditTerm;
    }
}

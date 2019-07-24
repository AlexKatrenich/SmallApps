package com.katrenich.alex.smartiwaycopy.mainModule.util;

import android.util.Log;

import com.katrenich.alex.smartiwaycopy.authModule.util.AuthController;
import com.katrenich.alex.smartiwaycopy.utils.AppStates;

import java.util.Observable;

public class UserActionController extends Observable {
    private static final UserActionController ourInstance = new UserActionController();
    private static final String TAG = "UserActionController";

    private UserActionController() {

    }

    public static UserActionController getInstance() {
        return ourInstance;
    }

    public void onAuthFragmentClick(){
        setChanged();
        notifyObservers(AppStates.AUTHORIZATION);
    }


    public void startApp() {
        if(!AuthController.getInstance().isUserLogged()){
            Log.i(TAG, "startApp: ");
            setChanged();
            notifyObservers(AppStates.REGISTER_PHONE_ENTER);
        } else {
            setChanged();
            notifyObservers(AppStates.MAIN_SCREEN_CREDIT);
        }
    }

    public void checkUserPhoneNumber() {
        setChanged();
        notifyObservers(AppStates.SMS_CODE_VALIDATION);
    }
}

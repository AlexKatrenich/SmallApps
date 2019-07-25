package com.katrenich.alex.smartiwaycopy.mainModule.util;

import android.util.Log;

import java.util.Observable;

public class MainActivityNavigateController extends Observable {
    private static final MainActivityNavigateController ourInstance = new MainActivityNavigateController();
    private static final String TAG = "MainActivityNC";

    public static MainActivityNavigateController getInstance() {
        return ourInstance;
    }

    private MainActivityNavigateController() {

    }

    public void navigate(Integer resID){
        Log.i(TAG, "navigate: " + resID);
        setChanged();
        notifyObservers(resID);
    }

}

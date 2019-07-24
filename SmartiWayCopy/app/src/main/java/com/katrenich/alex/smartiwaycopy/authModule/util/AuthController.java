package com.katrenich.alex.smartiwaycopy.authModule.util;

import com.katrenich.alex.smartiwaycopy.authModule.model.User;

import java.util.Observable;

public class AuthController extends Observable {
    private static AuthController mInstance;
    private User currentUser;
    private boolean userIsLogged;

    private AuthController() {
        userIsLogged = false;
    }

    public static AuthController getInstance() {
        if(mInstance == null) mInstance = new AuthController();
        return mInstance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isUserLogged() {
        return userIsLogged;
    }

    public void setUserIsLogged(boolean userIsLogged) {
        this.userIsLogged = userIsLogged;
    }
}

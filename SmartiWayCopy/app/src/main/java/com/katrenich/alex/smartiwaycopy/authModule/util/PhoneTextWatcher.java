package com.katrenich.alex.smartiwaycopy.authModule.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class PhoneTextWatcher implements TextWatcher {
    public static final String TAG = "PhoneTextWatcher";
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i(TAG, "beforeTextChanged: CharSequence= " + s + " Start= " + start + " Count= " + count + " After= " + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i(TAG, "onTextChanged: CharSequence= " + s + " Start= " + start + " Count= " + count + " Before= " + before);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i(TAG, "afterTextChanged: Editable= " + s);
    }
}

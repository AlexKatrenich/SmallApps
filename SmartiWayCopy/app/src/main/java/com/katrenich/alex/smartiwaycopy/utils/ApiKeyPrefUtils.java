package com.katrenich.alex.smartiwaycopy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.katrenich.alex.smartiwaycopy.R;

//This class stores and retrieves the API Key that needs to be sent in every HTTP call as Authorization header field.
public class ApiKeyPrefUtils {

    public ApiKeyPrefUtils() {

    }

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
    }

    public static void storeApiKey(Context context, String apiKey){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("API_KEY", apiKey);
        editor.apply();
    }

    public static String getApiKey(Context context){
        return getSharedPreferences(context).getString("API_KEY", null);
    }
}

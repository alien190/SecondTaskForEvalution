package com.example.ivanovnv.secondtaskforevalution;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by IvanovNV on 05.03.2018.
 */

public class SharedPreferencesHelper {

    private SharedPreferences mSharedPreferences;
    private static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    private static final String SHARED_PREF_VALUE = "SHARED_PREF_VALUE";
    private String initialValue;

    public SharedPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        initialValue = context.getResources().getString(R.string.tag_google);
    }

    public void writeValue(String value) {
        mSharedPreferences.edit().putString(SHARED_PREF_VALUE,value).apply();
    }

    public String readValue(){
        String ret = initialValue;

        try {
          ret = mSharedPreferences.getString(SHARED_PREF_VALUE,initialValue);
        }
        catch (Throwable t) {}

        return ret;
    }
}

package com.anngabr.perfection.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.anngabr.perfection.R;

/**
 * Created by Ann on 29.10.2017.
 */

public class Util {
    public static void setAppTheme(Context c) {
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(R.string.preference_theme_file_key), Context.MODE_PRIVATE);
        int themeId = sharedPref.getInt(c.getString(R.string.saved_theme_id), R.style.GreenLightBlueTheme);
        c.setTheme(themeId);
    }
}

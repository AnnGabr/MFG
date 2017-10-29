package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(readPlayerData())
            goToActivity(MenuActivity.class);
        else
            goToActivity(RegistrationActivity.class);

        finish();
    }

    private void applyTheme() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_theme_file_key), Context.MODE_PRIVATE);
        int themeId = sharedPref.getInt(getString(R.string.saved_theme_id), R.style.GreenLightBlueTheme);
        setTheme(themeId);
    }

    private boolean readPlayerData() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String nick = sharedPref.getString(getString(R.string.saved_player_nick), "");
        return !nick.isEmpty();
    }

    private void goToActivity(Class activityClass) {
        Intent gameActivity = new Intent(this, activityClass);
        startActivity(gameActivity);
    }
}

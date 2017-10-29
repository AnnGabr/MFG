package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anngabr.perfection.utils.Util;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setAppTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(readPlayerData())
            goToActivity(MenuActivity.class);
        else
            goToActivity(RegistrationActivity.class);

        finish();
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

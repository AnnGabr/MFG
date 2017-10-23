package com.anngabr.perfection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import player.PlayerDataWriteReader;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(readPlayerData())
            startActivity(MenuActivity.class);
        else
            startActivity(RegistrationActivity.class);

        finish();
    }

    private boolean readPlayerData() {
        try {
            PlayerDataWriteReader.readPlayerData(getApplicationContext());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void startActivity(Class activityClass) {
        Intent gameActivity = new Intent(this, activityClass);
        startActivity(gameActivity);
    }
}

package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anngabr.perfection.utils.MusicPlayer;
import com.anngabr.perfection.utils.Util;

import java.io.IOException;

import custom.listeners.OnSwipeTouchListener;

public class MenuActivity extends AppCompatActivity {

    private static MenuActivity instance;

    private Button startBtn;
    private Button recordBtn;
    private Button themeBtn;
    private TextView playerNameTextV;
    private TextView recordTextV;
    private TextView lastScoreTextV;

    private SharedPreferences sharedPref;

    @Override
    protected void onStart() {
        super.onStart();
        MusicPlayer.player = MediaPlayer.create(this, R.raw.menu);
        MusicPlayer.player.setLooping(true);
        MusicPlayer.player.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicPlayer.player.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPlayerData();
    }

    private void showPlayerData(){
        int last_score = sharedPref.getInt(getString(R.string.saved_last_score), 0);
        int high_score = sharedPref.getInt(getString(R.string.saved_high_score), 0);
        lastScoreTextV.setText(String.format("%d", last_score));
        recordTextV.setText(String.format("%d", high_score));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setAppTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        instance = this;

        setControls();
        setValues();
        setListeners();
    }

    private void setControls() {
        themeBtn = (Button) findViewById(R.id.themeBtn);
        startBtn = (Button) findViewById(R.id.startBtn);
        recordBtn = (Button) findViewById(R.id.recordBtn);
        playerNameTextV = (TextView) findViewById(R.id.playerNameTextV);
        recordTextV = (TextView) findViewById(R.id.recordTextV);
        lastScoreTextV = (TextView) findViewById(R.id.lastScoreTextV);
    }

    private void setValues() {
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String nick = sharedPref.getString(getString(R.string.saved_player_nick), "");
        playerNameTextV.setText(nick);
    }

    private void setListeners() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(GameActivity.class);
            }
        });

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getString(R.string.info_not_supported));
            }
        });

        themeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(ThemeActivity.class);
            }
        });

        findViewById(R.id.menuLayout).setOnTouchListener(new OnSwipeTouchListener(this){
            public void onSwipeLeft(){
                goToActivity(GameActivity.class);
            }
            public void onSwipeRight(){
                finish();
            }
        });
    }

    private void goToActivity(Class activityClass) {
        Intent gameActivity = new Intent(this, activityClass);
        startActivity(gameActivity);
    }

    private void showToast(String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    public static MenuActivity getInstance(){
        return instance;
    }
}

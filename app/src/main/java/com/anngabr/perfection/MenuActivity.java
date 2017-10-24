package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    private Button startBtn;
    private Button recordBtn;
    private TextView playerNameTextV;
    private TextView recordTextV;
    private TextView lastScoreTextV;

    private SharedPreferences sharedPref;

    @Override
    protected void onResume() {
        super.onResume();
        showPlayerData();
    }

    private void showPlayerData(){
        String nick = sharedPref.getString(getString(R.string.saved_player_nick), "");
        playerNameTextV.setText(nick);

        int last_score = sharedPref.getInt(getString(R.string.saved_last_score), 0);
        int high_score = sharedPref.getInt(getString(R.string.saved_high_score), 0);
        if(last_score > 0)
            lastScoreTextV.setText(String.format("%d", last_score));
        if(high_score > 0)
            recordTextV.setText(String.format("%d", high_score));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setControls();
        setValues();
        setListeners();
    }

    private void setControls() {
        startBtn = (Button) findViewById(R.id.startBtn);
        recordBtn = (Button) findViewById(R.id.recordBtn);
        playerNameTextV = (TextView) findViewById(R.id.playerNameTextV);
        recordTextV = (TextView) findViewById(R.id.recordTextV);
        lastScoreTextV = (TextView) findViewById(R.id.lastScoreTextV);
    }

    private void setValues() {
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
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
                goToActivity(RecordsActivity.class);
            }
        });
    }

    private void goToActivity(Class activityClass) {
        Intent gameActivity = new Intent(this, activityClass);
        startActivity(gameActivity);
    }
}

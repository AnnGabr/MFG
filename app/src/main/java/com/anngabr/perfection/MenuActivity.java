package com.anngabr.perfection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import db_manager.DBAdapter;
import player.Player;
import player.PlayerDataWriteReader;


public class MenuActivity extends AppCompatActivity {

    private static MenuActivity instance;

    private Button startBtn;
    private Button recordBtn;
    private TextView playerNameTextV;
    private TextView recordTextV;
    private TextView lastScoreTextV;

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
        instance = this;
        playerNameTextV.setText(Player.instance.getName());
        showPlayerScore();
    }

    private void setListeners() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.class);
            }
        });
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecordsActivity.class);
            }
        });
    }

    private void startActivity(Class activityClass) {
        Intent gameActivity = new Intent(this, activityClass);
        startActivity(gameActivity);
    }

    public static MenuActivity getInstance(){
        return instance;
    }

    public void showPlayerScore(){
        if(Player.instance.getRecord() > 0)
            recordTextV.setText(Integer.toString(Player.instance.getRecord()));
        if(Player.instance.getLastScore() > 0)
            lastScoreTextV.setText(Integer.toString(Player.instance.getLastScore()));
        else
            lastScoreTextV.setText("");
    }
}

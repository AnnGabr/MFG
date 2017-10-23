package com.anngabr.perfection;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import custom_controls.StartDialogFragment;
import db_manager.DBAdapter;
import game.GameController;
import player.Player;
import player.PlayerDataWriteReader;

import static com.anngabr.perfection.R.id.backBut;
import static com.anngabr.perfection.R.id.clearBut;
import static com.anngabr.perfection.R.id.val_0;
import static com.anngabr.perfection.R.id.val_1;
import static com.anngabr.perfection.R.id.val_2;
import static com.anngabr.perfection.R.id.val_3;
import static com.anngabr.perfection.R.id.val_4;
import static com.anngabr.perfection.R.id.val_5;
import static com.anngabr.perfection.R.id.val_6;
import static com.anngabr.perfection.R.id.val_7;
import static com.anngabr.perfection.R.id.val_8;
import static com.anngabr.perfection.R.id.val_9;


public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private static GameActivity gameActivityInstance;

    TextView playerInputTextV;
    TextView scoreTV;
    TextView digitsTV;

    GameController game;

    ObjectAnimator digitsViewAnimator;

    int numberOfDigits = 4;

    boolean loose;

    public static GameActivity getInstance(){
        return gameActivityInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setControls();
        setValues();
        setListeners();
        showStartDialog();
    }

    private void setControls() {
        digitsTV = (TextView)findViewById(R.id.digitsTextView);
        playerInputTextV = (TextView)findViewById(R.id.playerInputTextV);
        scoreTV = (TextView)findViewById(R.id.scoreTextView);
    }

    private void setValues() {
        setObjectAnimator();

        game = new GameController(numberOfDigits);
        gameActivityInstance = this;

        scoreTV.setText(Integer.toString(game.score));
        digitsTV.setVisibility(View.INVISIBLE);
    }

    private void setObjectAnimator() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        float toYDelta =  -screenHeight;
        digitsViewAnimator = ObjectAnimator.ofFloat(digitsTV, View.TRANSLATION_Y, toYDelta);
    }

    private void setListeners() {
        findViewById(R.id.clearBut).setOnClickListener(this);
        findViewById(R.id.backBut).setOnClickListener(this);
        findViewById(R.id.val_0).setOnClickListener(this);
        findViewById(R.id.val_1).setOnClickListener(this);
        findViewById(R.id.val_2).setOnClickListener(this);
        findViewById(R.id.val_3).setOnClickListener(this);
        findViewById(R.id.val_4).setOnClickListener(this);
        findViewById(R.id.val_5).setOnClickListener(this);
        findViewById(R.id.val_6).setOnClickListener(this);
        findViewById(R.id.val_7).setOnClickListener(this);
        findViewById(R.id.val_8).setOnClickListener(this);
        findViewById(R.id.val_9).setOnClickListener(this);

        digitsViewAnimator.addListener(new AnimatorListenerAdapter() {
            int duration = 8500;

            @Override
            public void onAnimationStart(Animator animation) {
                loose = true;
                digitsTV.setText(game.generateDigitSequence());
                animation.setDuration(duration + game.score*10);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(loose){
                    digitsViewAnimator.cancel();
                    goToMenuAndSaveResult();
                }
            }
        });
    }

    private void updateScore() {
        scoreTV.setText(Integer.toString(++game.score));
    }

    private void showStartDialog() {
        StartDialogFragment dialog = new StartDialogFragment();
        dialog.show(getFragmentManager(), "start dialog");
    }

    public void startGame() {
        Player.instance.setNewRecord(false);
        digitsTV.setVisibility(View.VISIBLE);
        digitsViewAnimator.start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (val_0):
            case (val_1):
            case (val_2):
            case (val_3):
            case (val_4):
            case (val_5):
            case (val_6):
            case (val_7):
            case (val_8):
            case (val_9): {
                showPlayerInput(((Button)findViewById(id)).getText());
                checkInput();
                break;
            }
            case (clearBut): {
                clearPlayerInput();
                break;
            }
            case (backBut): {
                goToMenuAndSaveResult();
                break;
            }
        }
    }

    private void showPlayerInput(CharSequence text) {
        if(playerInputTextV.getText().length() < 2)
            playerInputTextV.setText(playerInputTextV.getText() + "" + text);
    }

    private void checkInput() {
        String userInput = (String) playerInputTextV.getText();
        int playerCalculations = Integer.parseInt(userInput);
        if(game.getDigitSum() == playerCalculations)
        {
            loose = false;
            updateScore();
            clearPlayerInput();
            digitsViewAnimator.end();
            digitsViewAnimator.start();
        }
        else if(playerInputTextV.getText().length() > 1)
            clearPlayerInput();
    }

    private void clearPlayerInput() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playerInputTextV.setText("");
            }
        }, 300);
    }

    private void goToMenuAndSaveResult() {
        if(game.score < Player.instance.getRecord()){
            Player.instance.setLastScore(game.score);
        }
        else if(game.score != Player.instance.getLastScore()){
            Player.instance.setLastScore(0);
            Player.instance.setRecord(game.score);
            Player.instance.setNewRecord(true);
            updatePlayerRecordInDB();
        }

        updatePlayerInfoInFile();
        updatePlayerInfoInMenu();
        this.finish();
    }

    private void updatePlayerInfoInFile() {
        new Runnable(){
            @Override
            public void run() {
                try {
                    PlayerDataWriteReader.writePlayerData(getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.run();
    }

    private void updatePlayerRecordInDB() {
        new Runnable(){
            @Override
            public void run() {
                if (Player.instance.hasNewRecord()) {
                    DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                    dbAdapter.updatePlayerRecord(Player.instance.getName(), Player.instance.getRecord());
                    dbAdapter.closeDB();
                }
            }
        }.run();
    }

    private void updatePlayerInfoInMenu() {
        if (MenuActivity.getInstance() != null)
            MenuActivity.getInstance().showPlayerScore();
    }
}

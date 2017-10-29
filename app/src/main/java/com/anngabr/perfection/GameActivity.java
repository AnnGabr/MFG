package com.anngabr.perfection;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import custom.dialogs.ImageButDialogFragment;
import game.GameController;

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

    TextView playerInputTextV;
    TextView scoreTV;
    TextView digitsTV;

    ObjectAnimator digitsViewAnimator;

    GameController game;
    int numberOfDigits = 4;
    boolean loose;
    boolean paused;

    @Override
    protected void onPause() {
        super.onPause();
        if(digitsViewAnimator.isRunning()) {
            paused = true;
            digitsViewAnimator.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(paused)
            digitsViewAnimator.resume();
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
        paused = false;
        scoreTV.setText(String.format("%d", game.score));
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

    private void showStartDialog() {
        int title = R.string.game_rules;
        int img = R.drawable.game_rule;
        int btnText = R.string.start;
        ImageButDialogFragment dialog = new ImageButDialogFragment(img, btnText, title){
            @Override
            public void onClick(View v) {
                dismiss();
            }
            @Override
            public void onDismiss(DialogInterface dialog) {
                super.onDismiss(dialog);
                startGame();
            }
        };
        dialog.show(getFragmentManager(), "start dialog");
    }

    public void startGame() {
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
            clearPlayerInput();
            updateScore();
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

    private void updateScore() {
        scoreTV.setText(String.format("%d", ++game.score));
    }

    private void goToMenuAndSaveResult() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int high_score = sharedPref.getInt(getString(R.string.saved_high_score), 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(game.score < high_score){
            editor.putInt(getString(R.string.saved_last_score), game.score);
        }
        else {
            editor.putInt(getString(R.string.saved_high_score), game.score);
            editor.putInt(getString(R.string.saved_last_score), 0);
            editor.putBoolean(getString(R.string.saved_state_changed), true);
        }
        editor.commit();

        finish();
    }
}

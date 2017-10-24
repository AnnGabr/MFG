package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import db_manager.DBAdapter;
import player.Player;
import player.PlayerDataWriteReader;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nickEditText;

    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setControls();
        setValues();
        setListeners();
    }

    private void setControls(){
        nickEditText = (EditText) findViewById(R.id.nickEditText);
        nickEditText.requestFocus();
    }

    private void setValues() {
        dbAdapter = new DBAdapter(this);
    }

    private void setListeners() {
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToRegister();
            }
        });
        nickEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void tryToRegister() {
        String name = nickEditText.getText().toString();
        if(name.length() < 3) {
            showToast(getString(R.string.bad_password));
            return;
        }

        boolean added = dbAdapter.addPlayer(name);
        if(added) {
            saveToSharedPref(name);
            showToast(getString(R.string.registered));
            goToActivity(MenuActivity.class);
        }
        else
            showToast(getString(R.string.not_registered));
    }

    private void saveToSharedPref(String name) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_player_nick), name);
        editor.putInt(getString(R.string.saved_high_score), 0);
        editor.putInt(getString(R.string.saved_last_score), 0);
        editor.commit();
    }

    private void goToActivity(Class activityClass) {
        Intent activity = new Intent(this, activityClass);
        startActivity(activity);
        finish();
    }

    private void showToast(String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.closeDB();
    }
}

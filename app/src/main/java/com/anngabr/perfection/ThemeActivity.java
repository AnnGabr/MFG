package com.anngabr.perfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.anngabr.perfection.utils.Util;

import java.util.ArrayList;

import custom.adapters.theme_adapter.Theme;
import custom.adapters.theme_adapter.ThemeAdapter;
import custom.dialogs.ImageButDialogFragment;

public class ThemeActivity extends AppCompatActivity{

    Theme selectedTheme;
    ListView recordsListV;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setAppTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        setControls();
        setListeners();
        fillList();
    }

    private void setControls() {
        recordsListV = (ListView) findViewById(R.id.themeListV);
    }

    private void setListeners() {
        recordsListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThemeAdapter ta = (ThemeAdapter) parent.getAdapter();
                selectedTheme = ta.getItem(position);
                showApplyDialog();
            }
        });
    }

    private void showApplyDialog() {
        ImageButDialogFragment dialog = new ImageButDialogFragment(selectedTheme.getImgResId(), R.string.apply, selectedTheme.getName()){
            @Override
            public void onClick(View v) {
                dismiss();
                setContentView(R.layout.activity_splash);

                rememberSelectedTheme();
                goToMenuActivity();

                finish();
            }
        };

        dialog.show(getFragmentManager(), "apply dialog");
    }

    private void rememberSelectedTheme() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_theme_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_theme_id), selectedTheme.getThemeResId());
        editor.commit();
    }

    public void goToMenuActivity() {
        if(MenuActivity.getInstance() != null)
            MenuActivity.getInstance().finish();
        Intent gameActivity = new Intent(this, MenuActivity.class);
        startActivity(gameActivity);
    }

    private void fillList() {
        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(new Theme(R.string.glb_theme_name, R.drawable.green_light_blue, R.style.GreenLightBlueTheme));
        themes.add(new Theme(R.string.pa_theme_name, R.drawable.purple_addict, R.style.PurpleAddictTheme));

        recordsListV.setAdapter(new ThemeAdapter(this, themes));
    }
}

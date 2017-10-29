package com.anngabr.perfection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import custom.adapters.theme_adapter.Theme;
import custom.adapters.theme_adapter.ThemeAdapter;

public class ThemeActivity extends AppCompatActivity{

    ListView recordsListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        setControls();
        fillList();
    }

    private void setControls() {
        recordsListV = (ListView) findViewById(R.id.themeListV);
    }

    private void fillList() {
        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(new Theme(getString(R.string.glb_theme_name), R.drawable.green_light_blue, R.style.GreenLightBlueTheme));
        themes.add(new Theme(getString(R.string.pa_theme_name), R.drawable.purple_addict, R.style.PurpleAddictTheme));

        recordsListV.setAdapter(new ThemeAdapter(this, themes));
    }
}

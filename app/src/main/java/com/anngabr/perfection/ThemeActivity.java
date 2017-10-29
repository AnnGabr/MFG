package com.anngabr.perfection;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import custom.adapters.theme_adapter.Theme;
import custom.adapters.theme_adapter.ThemeAdapter;
import custom.dialogs.ImageButDialogFragment;

public class ThemeActivity extends AppCompatActivity{

    ListView recordsListV;
    ArrayList<Theme> themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Theme t = ta.getItem(position);
                showApplyDialog(t);
            }
        });
    }

    private void showApplyDialog(Theme t) {
        ImageButDialogFragment dialog = new ImageButDialogFragment(t.getImgResId(), R.string.apply, t.getName()){
            @Override
            public void onClick(View v) {
                dismiss();
            }
            @Override
            public void onDismiss(DialogInterface dialog) {
                super.onDismiss(dialog);
            }
        };

        dialog.show(getFragmentManager(), "apply dialog");
    }

    private void fillList() {
        themes = new ArrayList<>();
        themes.add(new Theme(R.string.glb_theme_name, R.drawable.green_light_blue, R.style.GreenLightBlueTheme));
        themes.add(new Theme(R.string.pa_theme_name, R.drawable.purple_addict, R.style.PurpleAddictTheme));

        recordsListV.setAdapter(new ThemeAdapter(this, themes));
    }
}

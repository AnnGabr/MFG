package com.anngabr.perfection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import custom.adapters.player_adapter.PlayerAdapter;
import db_manager.DBAdapter;

public class RecordsActivity extends AppCompatActivity {

    ListView recordsListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        setControls();
        fillList();
    }

    private void setControls() {
        recordsListV = (ListView) findViewById(R.id.recordsListV);
    }

    private void fillList() {
        DBAdapter dbAdapter = new DBAdapter(this);

        int N = 10;
        recordsListV.setAdapter(new PlayerAdapter(this, dbAdapter.getFirstNPlayersInTop(N)));
    }
}

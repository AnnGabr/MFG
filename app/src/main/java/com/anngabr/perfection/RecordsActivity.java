package com.anngabr.perfection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import db_manager.DBAdapter;
import player.Player;

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

        int recCount = 10;
        recordsListV.setAdapter(new PlayerAdapter(this, dbAdapter.getFirstNPlayersInTop(recCount)));
    }

    private class PlayerAdapter extends ArrayAdapter<Player> {

        public PlayerAdapter(@NonNull Context context, ArrayList<Player> players) {
            super(context, R.layout.records_list_item, players);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Player player = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.records_list_item, null);
            }
            ((TextView) convertView.findViewById(R.id.rl_nameTextV))
                    .setText(player.getName());
            ((TextView) convertView.findViewById(R.id.rl_recordTextV))
                    .setText(Integer.toString(player.getRecord()));

            return convertView;
        }
    }
}

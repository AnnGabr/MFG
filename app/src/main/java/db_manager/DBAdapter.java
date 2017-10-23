package db_manager;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.solver.ArrayLinkedVariables;

import java.util.ArrayList;

import player.Player;

/**
 * Created by Ann on 19.10.2017.
 */

public class DBAdapter {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean addPlayer(String playerName){
        ContentValues cv = new ContentValues();
        cv.put("name", playerName);
        cv.put("record", 0);
        long res = db.insert(DBHelper.USERS_TABLE_NAME, null, cv);
        if(res == -1)
            return false;
        return true;
    }

    public boolean updatePlayerRecord(String playerName, int record){
        ContentValues cv = new ContentValues();
        cv.put("record", record);
        int numOfRowsEffected = db.update(DBHelper.USERS_TABLE_NAME, cv, "" + DBHelper.NAME_COL + "=?", new String[]{playerName});

        return (numOfRowsEffected == 0) ? false:true;
    }

    public ArrayList<Player> getFirstNPlayersInTop(int n){
        ArrayList<Player> players = new ArrayList<>();

        if(n < 1)
            return players;

        String selectQuery = "SELECT  * FROM " + DBHelper.USERS_TABLE_NAME
                + " ORDER BY " + DBHelper.RECORD_COL + " DESC;";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            int nameColIndex = c.getColumnIndex(DBHelper.NAME_COL);
            int recordColIndex = c.getColumnIndex(DBHelper.RECORD_COL);
            do {
                players.add(new Player(c.getString(nameColIndex),c.getInt(recordColIndex)));
                --n;
            } while (c.moveToNext() && n > 0);
        }
        c.close();

        return players;
    }

    public void closeDB(){
        dbHelper.close();
    }
}

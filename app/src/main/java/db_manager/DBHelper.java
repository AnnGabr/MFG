package db_manager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ann on 19.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "usersDB";
    public static final String USERS_TABLE_NAME = "users";
    public static final String NAME_COL = "name";
    public static final String RECORD_COL = "record";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(null, "--- onCreate database ---");
        db.execSQL("create table " + USERS_TABLE_NAME + " ( "
                + NAME_COL + " text primary key, "
                + RECORD_COL + " integer" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }
}

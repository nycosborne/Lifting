package com.nycosborne.lifting.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by danielosborne on 4/17/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_FILE_NAME = "lifting4.db";
    public static final int DB_VERSION = 1;
    private static final String TAG = "DBHelper";

    public DBHelper(Context context){
        super(context, DB_FILE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WorkOutsTabele.SQL_CREATE);
        db.execSQL(ExercisesTable.SQL_CREATE);
        db.execSQL(DisplaySetTable.SQL_CREATE);
        db.execSQL(ResultsTable.SQL_CREATE);
        Log.i(TAG, "Building databases");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(WorkOutsTabele.SQL_DELETE);
        db.execSQL(ExercisesTable.SQL_DELETE);
        db.execSQL(DisplaySetTable.SQL_DELETE);
        db.execSQL(ResultsTable.SQL_DELETE);

        onCreate(db);
    }
}

package com.nycosborne.lifting.database;

/**
 * Created by danielosborne on 4/25/17.
 */

public class DisplaySetTable {
    public static final String TABLE_DISPLAY_SET = "displaySet";
    public static final String COLUMN_ID = "displaySetID";
    public static final String COLUMN_EXERCISE_NAME = "exerciseName";
    public static final String COLUMN_WIGHT = "wight";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_SET = "sets";
    public static final String COLUMN_EXERCISE_ID = "exerciseId";
    public static final String COLUMN_CHECKED = "isChecked";

    public static final String [] ALL_COLUMN =
            {COLUMN_ID,COLUMN_EXERCISE_NAME,COLUMN_WIGHT,COLUMN_REPS,
                    COLUMN_SET,COLUMN_EXERCISE_ID,COLUMN_CHECKED};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_DISPLAY_SET + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_EXERCISE_NAME + " TEXT," +
                    COLUMN_WIGHT + " INTEGER," +
                    COLUMN_REPS + " INTEGER," +
                    COLUMN_SET + " TEXT," +
                    COLUMN_EXERCISE_ID + " TEXT," +
                    COLUMN_CHECKED + " INTEGER" + ");";




    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_DISPLAY_SET;

}

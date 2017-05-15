package com.nycosborne.lifting.database;

/**
 * Created by danielosborne on 4/21/17.
 */

public class ResultsTable {
    public static final String TABLE_RESULTS = "results";
    public static final String COLUMN_ID = "resultsID";
    public static final String COLUMN_REP_RESULT = "reps";
    public static final String COLUMN_WIGHT_RESULT = "wight";
    public static final String COLUMN_DATETIME = "timeStamp";
    public static final String COLUMN_DISPLAY_SET_ID = "displaySetId";


    public static final String [] ALL_COLUMN =
            {COLUMN_ID,COLUMN_REP_RESULT,COLUMN_WIGHT_RESULT,COLUMN_DATETIME,COLUMN_DISPLAY_SET_ID};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_RESULTS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_REP_RESULT + " INTEGER," +
                    COLUMN_WIGHT_RESULT + " INTEGER," +
                    COLUMN_DATETIME + " TEXT," +
                    COLUMN_DISPLAY_SET_ID + " TEXT" + ");";




    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_RESULTS;

}

package com.nycosborne.lifting.database;

/**
 * Created by danielosborne on 4/17/17.
 */

public class WorkOutsTabele {
    public static final String TABLE_WORKOUTS = "worksOuts";
    public static final String COLUMN_WORKOUTID = "workOutId";
    public static final String COLUMN_NAME = "workOutName";
    public static final String COLUMN_DAY = "workOutDay";
    public static final String COLUMN_POSITION = "sortPosition";

    public static final String [] ALL_COLUMN =
            {COLUMN_WORKOUTID,COLUMN_NAME,COLUMN_DAY,COLUMN_POSITION};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_WORKOUTS + "(" +
                    COLUMN_WORKOUTID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DAY + " TEXT," +
                    COLUMN_POSITION + " INTEGER" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_WORKOUTS;

}

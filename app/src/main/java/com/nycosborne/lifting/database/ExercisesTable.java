package com.nycosborne.lifting.database;

/**
 * Created by danielosborne on 4/18/17.
 */

public class ExercisesTable {

    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_ID = "exerciseId";
    public static final String COLUMN_NAME = "exerciseName";
    public static final String COLUMN_WIGHT = "wight";
    public static final String COLUMN_REPS = "reps";
    public static final String COLUMN_SETS = "sets";
    public static final String COLUMN_POSITION = "sortPosition";
    public static final String COLUMN_REST_TIME = "restTime";
    public static final String COLUMN_WORKOUTID = "workOutId";


    public static final String [] ALL_COLUMN =
            {COLUMN_ID,COLUMN_NAME,COLUMN_WIGHT,COLUMN_REPS,COLUMN_SETS,
                    COLUMN_POSITION,COLUMN_REST_TIME,COLUMN_WORKOUTID};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_EXERCISES + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_WIGHT + " INTEGER," +
                    COLUMN_REPS + " INTEGER," +
                    COLUMN_SETS + " INTEGER," +
                    COLUMN_POSITION + " INTEGER," +
                    COLUMN_REST_TIME + " INTEGER," +
                    COLUMN_WORKOUTID + " TEXT" + ");";


    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_EXERCISES;




}

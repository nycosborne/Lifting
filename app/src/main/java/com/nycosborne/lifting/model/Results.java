package com.nycosborne.lifting.model;

import android.content.ContentValues;

import com.nycosborne.lifting.database.ResultsTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by danielosborne on 4/21/17.
 */

public class Results {

    private String resultsID;
    private int reps;
    private int wight;
    private String timeStamp;
    private String displaySetId;

    public Results() {
    }

    public Results(int reps, int wight, String displaysetId) {

        if(resultsID == null){
            resultsID = UUID.randomUUID().toString();
        }


        this.reps = reps;
        this.wight = wight;
        this.timeStamp = getDateTime();
        this.displaySetId = displaysetId;
    }



    public void setResultsID(String resultsID) {
        this.resultsID = resultsID;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getResultsID() {
        return resultsID;
    }

    public void setExerciseId(String exerciseId) {
        this.displaySetId = exerciseId;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM/dd/yy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public ContentValues toValue() {
        ContentValues values = new ContentValues(5);
        values.put(ResultsTable.COLUMN_ID, resultsID);
        values.put(ResultsTable.COLUMN_REP_RESULT, reps);
        values.put(ResultsTable.COLUMN_WIGHT_RESULT, wight);
        values.put(ResultsTable.COLUMN_DATETIME, getDateTime());
        values.put(ResultsTable.COLUMN_DISPLAY_SET_ID, displaySetId);

        return values;

    }


}

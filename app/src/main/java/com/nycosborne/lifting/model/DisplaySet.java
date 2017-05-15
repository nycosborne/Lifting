package com.nycosborne.lifting.model;

import android.content.ContentValues;
import android.util.Log;

import java.util.UUID;

import com.nycosborne.lifting.database.DisplaySetTable;

/**
 * Created by danielosborne on 4/19/17.
 */

public class DisplaySet {

    private String displaySetId;
    private String exerciseName;
    private int wight;
    private int reps;
    private int sets;
    private String exerciseId;
    private int isChecked;
    public DisplaySet(){

    }
    public DisplaySet(Exercise mExercise) {
        Log.d("DisplaySet","Building with just Exercise obj");

        if(displaySetId == null){
            displaySetId = UUID.randomUUID().toString();
        }
        this.wight = mExercise.getWight();
        this.reps = mExercise.getReps();
        this.sets = mExercise.getSets();
        this.exerciseId = mExercise.getExerciseId();
        this.exerciseName = mExercise.getExerciseName();
        isChecked = 0;
    }


    public String getDisplaySetId() {
        return displaySetId;
    }

    public void setDisplaySetId(String displaySetId) {
        this.displaySetId = displaySetId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public ContentValues toValue() {
        ContentValues values = new ContentValues(7);
        values.put(DisplaySetTable.COLUMN_ID, displaySetId);
        values.put(DisplaySetTable.COLUMN_EXERCISE_NAME, exerciseName);
        values.put(DisplaySetTable.COLUMN_WIGHT, wight);
        values.put(DisplaySetTable.COLUMN_REPS, reps);
        values.put(DisplaySetTable.COLUMN_SET, sets);
        values.put(DisplaySetTable.COLUMN_EXERCISE_ID, exerciseId);
        values.put(DisplaySetTable.COLUMN_CHECKED, isChecked);

        return values;

    }

}

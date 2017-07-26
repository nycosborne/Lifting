package com.nycosborne.lifting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nycosborne.lifting.model.DisplaySet;
import com.nycosborne.lifting.model.Exercise;
import com.nycosborne.lifting.model.Results;
import com.nycosborne.lifting.model.WorkOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielosborne on 4/17/17.
 */

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabases;
    private SQLiteDatabase mDatabases2;
    private SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabases = mDbHelper.getWritableDatabase();
        mDatabases2 = mDbHelper.getWritableDatabase();
    }
    public void open(){
        mDatabases = mDbHelper.getWritableDatabase();
    }
    public void close(){
        mDbHelper.close();
    }

    public WorkOut createWorkOutItem(WorkOut item){
        ContentValues values = item.toValue();
        mDatabases.insert(WorkOutsTabele.TABLE_WORKOUTS, null, values);

        return item;

    }

    public Exercise createExercisesItem(Exercise exercise){
        ContentValues values = exercise.toValue();
        mDatabases.insert(ExercisesTable.TABLE_EXERCISES, null, values);

        return exercise;
    }


    public DisplaySet createDisplaySetItem(DisplaySet displaySet){
        ContentValues values = displaySet.toValue();
        mDatabases.insert(DisplaySetTable.TABLE_DISPLAY_SET, null, values);

        return displaySet;
    }

    public Results createResultsItem(Results results){
        ContentValues values = results.toValue();
        mDatabases.insert(ResultsTable.TABLE_RESULTS, null, values);

        return results;
    }

    public long getDataWorkOutItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabases, WorkOutsTabele.TABLE_WORKOUTS);
    }

    public List<WorkOut> getAllWorkOuts(String category){
        Cursor cursor = null;
        if (category == null) {
            cursor = mDatabases.query(WorkOutsTabele.TABLE_WORKOUTS, WorkOutsTabele.ALL_COLUMN,
                    null, null, null, null, WorkOutsTabele.COLUMN_NAME);
        }else {
            String [] categories = {category};
            cursor = mDatabases.query(WorkOutsTabele.TABLE_WORKOUTS, WorkOutsTabele.ALL_COLUMN,
                    WorkOutsTabele.COLUMN_DAY + "=?" , categories, null, null, WorkOutsTabele.COLUMN_NAME);
        }
        List<WorkOut> dataItems = new ArrayList<>();

        while (cursor.moveToNext()){
            WorkOut item = new WorkOut();
            item.setWorkOutId(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_WORKOUTID)));
            item.setWorkOutName(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_NAME)));
            item.setWorkOutDay(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_DAY)));
            item.setSortPosition(cursor.getInt(cursor.getColumnIndex(WorkOutsTabele.COLUMN_POSITION)));

            dataItems.add(item);

        }

        cursor.close();
        return  dataItems;

    }



    public List<Exercise> getExercisesById(String exerciseId) {
        Cursor cursor = null;

        if (exerciseId == null) {
            cursor = null;
        }else {
            String [] exerciseIds = {exerciseId};
            cursor = mDatabases.query(ExercisesTable.TABLE_EXERCISES, ExercisesTable.ALL_COLUMN,
                    ExercisesTable.COLUMN_ID + "=?" , exerciseIds, null, null, ExercisesTable.COLUMN_NAME);
        }

        List<Exercise> dataItems = new ArrayList<>();
        while (cursor.moveToNext()){
            Exercise item = new Exercise();
            item.setExerciseId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_ID)));
            item.setExerciseName(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_NAME)));
            item.setWight(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_WIGHT)));
            item.setReps(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_REPS)));
            item.setSets(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_SETS)));
            item.setSortPosition(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_POSITION)));
            item.setRestTime(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_REST_TIME)));
            item.setWorkOutId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_WORKOUTID)));

            dataItems.add(item);

        }
        cursor.close();

        return  dataItems;

    }
    public List<WorkOut> getWorkOutById(String workOutId) {
        Cursor cursor = null;


        if (workOutId == null) {
            cursor = null;
        }else {
            String [] workOutIds = {workOutId};
            cursor = mDatabases.query(WorkOutsTabele.TABLE_WORKOUTS, WorkOutsTabele.ALL_COLUMN,
                    WorkOutsTabele.COLUMN_WORKOUTID + "=?" , workOutIds, null, null, WorkOutsTabele.COLUMN_NAME);
        }


        List<WorkOut> dataItems = new ArrayList<>();
        while (cursor.moveToNext()){
            WorkOut item = new WorkOut();
            item.setWorkOutId(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_WORKOUTID)));
            item.setWorkOutName(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_NAME)));
            item.setWorkOutDay(cursor.getString(cursor.getColumnIndex(WorkOutsTabele.COLUMN_DAY)));
            item.setSortPosition(cursor.getInt(cursor.getColumnIndex(WorkOutsTabele.COLUMN_POSITION)));

            dataItems.add(item);

        }
        cursor.close();

        return  dataItems;

    }
    public List<Exercise> getExercisesByWorkOutId(String workOutId) {
        Cursor cursor = null;


        if (workOutId == null) {
            cursor = null;
        }else {
            String [] workOutIds = {workOutId};
            cursor = mDatabases.query(ExercisesTable.TABLE_EXERCISES, ExercisesTable.ALL_COLUMN,
                    ExercisesTable.COLUMN_WORKOUTID + "=?" , workOutIds, null, null, ExercisesTable.COLUMN_NAME);
        }


        List<Exercise> dataItems = new ArrayList<>();
        while (cursor.moveToNext()){
            Exercise item = new Exercise();
            item.setExerciseId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_ID)));
            item.setExerciseName(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_NAME)));
            item.setWight(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_WIGHT)));
            item.setReps(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_REPS)));
            item.setSets(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_SETS)));
            item.setSortPosition(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_POSITION)));
            item.setRestTime(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_REST_TIME)));
            item.setWorkOutId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_WORKOUTID)));

            dataItems.add(item);

        }
   cursor.close();

        return  dataItems;

    }

    public List<Exercise> getAllExercise(String category){
        Cursor cursor = null;
        if (category == null) {
            cursor = mDatabases.query(ExercisesTable.TABLE_EXERCISES, ExercisesTable.ALL_COLUMN,
                    null, null, null, null, ExercisesTable.COLUMN_NAME);
        }
//        else {
//            String [] categories = {category};
//            cursor = mDatabases.query(WorkOutsTabele.TABLE_WORKOUTS, WorkOutsTabele.ALL_COLUMN,
//                    WorkOutsTabele.COLUMN_DAY + "=?" , categories, null, null, WorkOutsTabele.COLUMN_NAME);
//        }
        List<Exercise> dataItems = new ArrayList<>();


        while (cursor.moveToNext()){
            Exercise item = new Exercise();
            item.setExerciseId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_ID)));
            item.setExerciseName(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_NAME)));
            item.setWight(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_WIGHT)));
            item.setReps(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_REPS)));
            item.setSets(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_SETS)));
            item.setSortPosition(cursor.getInt(cursor.getColumnIndex(ExercisesTable.COLUMN_POSITION)));
            item.setWorkOutId(cursor.getString(cursor.getColumnIndex(ExercisesTable.COLUMN_WORKOUTID)));




            dataItems.add(item);

        }


        cursor.close();
        return  dataItems;

    }


    public List<Results> getResultsByDisplaySetId(String displaySetId) {
        Cursor cursor = null;


        if (displaySetId == null) {
            cursor = null;
        }else {
            String [] displaySetIds = {displaySetId};
            cursor = mDatabases.query(ResultsTable.TABLE_RESULTS, ResultsTable.ALL_COLUMN,
                    ResultsTable.COLUMN_DISPLAY_SET_ID + "=?" , displaySetIds, null, null, ResultsTable.COLUMN_DATETIME + " DESC");

           // cursor = mDatabases.rawQuery("Select " + ResultsTable.COLUMN_REP_RESULT + " FROM " + ResultsTable.TABLE_RESULTS, null);

        }


        List<Results> dataItems = new ArrayList<>();

        while (cursor.moveToNext()){
            Results item = new Results();
            item.setResultsID(cursor.getString(cursor.getColumnIndex(ResultsTable.COLUMN_ID)));
            item.setWight(cursor.getInt(cursor.getColumnIndex(ResultsTable.COLUMN_WIGHT_RESULT)));
            item.setReps(cursor.getInt(cursor.getColumnIndex(ResultsTable.COLUMN_REP_RESULT)));
            item.setTimeStamp(cursor.getString(cursor.getColumnIndex(ResultsTable.COLUMN_DATETIME)));
            item.setExerciseId(cursor.getString(cursor.getColumnIndex(ResultsTable.COLUMN_DISPLAY_SET_ID)));

            dataItems.add(item);
        }
        cursor.close();

        return  dataItems;

    }

    public List<Results> getResultsDataPoint(String[] id1) {
        Cursor cursor = null;

        String queryTest = "SELECT SUM("+ResultsTable.COLUMN_WIGHT_RESULT+"),SUM("+ResultsTable.COLUMN_REP_RESULT+")," +
                " "+ResultsTable.COLUMN_DATETIME+" FROM "+ResultsTable.TABLE_RESULTS+" where "+ResultsTable.COLUMN_DISPLAY_SET_ID+" =";

        for (int i = 0; i < id1.length; i++) {

            if (i+1<id1.length) {
                queryTest = queryTest.concat("'" + id1[i] + "'" + " or "+ResultsTable.COLUMN_DISPLAY_SET_ID+" =");
            }else {
                queryTest = queryTest.concat("'" + id1[i] + "'");
            }
        }
        queryTest = queryTest.concat(" GROUP BY "+ResultsTable.COLUMN_DATETIME+"");
        Log.d("queryCheck", "getResultsDataPoint: " + queryTest);

        cursor = mDatabases.rawQuery(queryTest,null);
        List<Results> dataItems = new ArrayList<>();

        while (cursor.moveToNext()){
            Results item = new Results();
            item.setWight(cursor.getInt(0));
            item.setReps(cursor.getInt(1));
            item.setTimeStamp(cursor.getString(cursor.getColumnIndex(ResultsTable.COLUMN_DATETIME)));


            dataItems.add(item);
        }
        cursor.close();

        return  dataItems;
    }


   public List<DisplaySet> getDisplaySetByExerciseId(String exceriseId) {
       Cursor cursor = null;


       if (exceriseId == null) {
           cursor = null;
       }else {
           String [] exceriseIds = {exceriseId};
           cursor = mDatabases.query(DisplaySetTable.TABLE_DISPLAY_SET, DisplaySetTable.ALL_COLUMN,
                   DisplaySetTable.COLUMN_EXERCISE_ID + "=?" , exceriseIds, null, null, DisplaySetTable.COLUMN_EXERCISE_ID);

           // cursor = mDatabases.rawQuery("Select " + ResultsTable.COLUMN_REP_RESULT + " FROM " + ResultsTable.TABLE_RESULTS, null);

       }


       List<DisplaySet> dataItems = new ArrayList<>();

       while (cursor.moveToNext()){
           DisplaySet item = new DisplaySet();
           item.setDisplaySetId(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_ID)));
           item.setExerciseName(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_EXERCISE_NAME)));
           item.setWight(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_WIGHT)));
           item.setReps(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_REPS)));
           item.setSets(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_SET)));
           item.setExerciseId(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_EXERCISE_ID)));
           item.setIsChecked(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_CHECKED)));
           dataItems.add(item);
       }
       cursor.close();

       return  dataItems;

   }

    public List<DisplaySet> getDisplaySetByDisplaySetId(String displaySetId) {
        Cursor cursor = null;


        if (displaySetId == null) {
            cursor = null;
        }else {
            String [] displaySetIds = {displaySetId};
            cursor = mDatabases.query(DisplaySetTable.TABLE_DISPLAY_SET, DisplaySetTable.ALL_COLUMN,
                    DisplaySetTable.COLUMN_ID + "=?" , displaySetIds, null, null, DisplaySetTable.COLUMN_EXERCISE_ID);

            // cursor = mDatabases.rawQuery("Select " + ResultsTable.COLUMN_REP_RESULT + " FROM " + ResultsTable.TABLE_RESULTS, null);

        }
        List<DisplaySet> dataItems = new ArrayList<>();

        while (cursor.moveToNext()){
            DisplaySet item = new DisplaySet();
            item.setDisplaySetId(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_ID)));
            item.setExerciseName(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_EXERCISE_NAME)));
            item.setWight(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_WIGHT)));
            item.setReps(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_REPS)));
            item.setSets(cursor.getInt(cursor.getColumnIndex(DisplaySetTable.COLUMN_SET)));
            item.setExerciseId(cursor.getString(cursor.getColumnIndex(DisplaySetTable.COLUMN_EXERCISE_ID)));

            dataItems.add(item);
        }
        cursor.close();

        return  dataItems;

    }








   public int updateWorkOutName(WorkOut workOut, String newWorkOutName){

        ContentValues values = workOut.toValue();
        values.put(WorkOutsTabele.COLUMN_NAME,newWorkOutName);

        String [] workOutIds = {workOut.getWorkOutId()};
        int count = mDatabases.update(WorkOutsTabele.TABLE_WORKOUTS,values,
                WorkOutsTabele.COLUMN_WORKOUTID+" =?",workOutIds);
        return count;


    }
    public int updateWorkOutDay(WorkOut workOut, String newWorkOutDay){

        ContentValues values = workOut.toValue();
        values.put(WorkOutsTabele.COLUMN_DAY,newWorkOutDay);

        String [] workOutIds = {workOut.getWorkOutId()};
        int count = mDatabases.update(WorkOutsTabele.TABLE_WORKOUTS,values,
                WorkOutsTabele.COLUMN_WORKOUTID+" =?",workOutIds);
        return count;


    }

    // TODO: 7/20/17 need to see the I can update rest time
    public int updateExceriseRestTime(Exercise exercise, String newExerciseName){

        ContentValues values = exercise.toValue();
        values.put(ExercisesTable.COLUMN_NAME,newExerciseName);


        DisplaySet displaySet = new DisplaySet();
        ContentValues values2 = displaySet.toValue();
        values2.put(DisplaySetTable.COLUMN_EXERCISE_NAME,newExerciseName);


        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.update(ExercisesTable.TABLE_EXERCISES,values,
                ExercisesTable.COLUMN_ID+" =?",exerciseIds);

        mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values2,
                DisplaySetTable.COLUMN_EXERCISE_ID+" =?",exerciseIds);

        return count;


    }

    public int updateExerciseName(Exercise exercise, String newExerciseName){

        ContentValues values = exercise.toValue();
        values.put(ExercisesTable.COLUMN_NAME,newExerciseName);



        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.update(ExercisesTable.TABLE_EXERCISES,values,
                ExercisesTable.COLUMN_ID+" =?",exerciseIds);


        return count;


    }


    public int updateDisplaySetName(DisplaySet displaySet, String newExerciseName){
        ContentValues values = displaySet.toValue();
        values.put(DisplaySetTable.COLUMN_EXERCISE_NAME,newExerciseName);

        String [] displaySetIds = {displaySet.getDisplaySetId()};
        int count = mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values,
                DisplaySetTable.COLUMN_ID+" =?",displaySetIds);

        return count;

    }



    public int updateExerciseWeight(Exercise exercise, int newExerciseWight){

        ContentValues values = exercise.toValue();
        values.put(ExercisesTable.COLUMN_WIGHT,newExerciseWight);


        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.update(ExercisesTable.TABLE_EXERCISES,values,
                ExercisesTable.COLUMN_ID+" =?",exerciseIds);


        return count;

    }


    public int updateDisplaySetWight(DisplaySet displaySet, int newExerciseWight){

        ContentValues values = displaySet.toValue();
        values.put(DisplaySetTable.COLUMN_WIGHT,newExerciseWight);
        String [] displaySetId = {displaySet.getDisplaySetId()};
        int count = mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values,
                DisplaySetTable.COLUMN_ID+" =?",displaySetId);

        return count;
    }





    public int updateExerciseReps(Exercise exercise, int newExerciseReps){

        ContentValues values = exercise.toValue();
        values.put(ExercisesTable.COLUMN_REPS,newExerciseReps);


        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.update(ExercisesTable.TABLE_EXERCISES,values,
                ExercisesTable.COLUMN_ID+" =?",exerciseIds);

        return count;

    }

    public int updateDisplaySetReps(DisplaySet displaySet, int newExerciseReps){

        ContentValues values = displaySet.toValue();
        values.put(DisplaySetTable.COLUMN_REPS,newExerciseReps);
        String [] displaySetId = {displaySet.getDisplaySetId()};
        int count = mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values,
                DisplaySetTable.COLUMN_ID+" =?",displaySetId);

        return count;
    }

    public int updateExerciseSets(Exercise exercise, int newExerciseSets){

        ContentValues values = exercise.toValue();
        values.put(ExercisesTable.COLUMN_SETS,newExerciseSets);
        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.update(ExercisesTable.TABLE_EXERCISES,values,
                ExercisesTable.COLUMN_ID+" =?",exerciseIds);

        return count;

    }

    public int updateDisplaySetSets(DisplaySet displaySet, int newExerciseSets){

        ContentValues values = displaySet.toValue();
        values.put(DisplaySetTable.COLUMN_SET,newExerciseSets);
        String [] displaySetId = {displaySet.getDisplaySetId()};
        int count = mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values,
                DisplaySetTable.COLUMN_ID+" =?",displaySetId);

        return count;
    }


    public int updateWorkOutNameAndDay(WorkOut workOut,String newWorkOutName, String newWorkOutDay){

        ContentValues values = workOut.toValue();
        values.put(WorkOutsTabele.COLUMN_NAME,newWorkOutName);
        values.put(WorkOutsTabele.COLUMN_DAY,newWorkOutDay);

        String [] workOutIds = {workOut.getWorkOutId()};
        int count = mDatabases.update(WorkOutsTabele.TABLE_WORKOUTS,values,
                WorkOutsTabele.COLUMN_WORKOUTID+" =?",workOutIds);
        return count;


    }

    public int upDateDisplaySetCheck(DisplaySet displaySet, int check){

        ContentValues values = displaySet.toValue();
        values.put(DisplaySetTable.COLUMN_CHECKED,check);

        String [] displaySetIds = {displaySet.getDisplaySetId()};
        int count = mDatabases.update(DisplaySetTable.TABLE_DISPLAY_SET,values,
                DisplaySetTable.COLUMN_ID+" =?",displaySetIds);
        return count;


    }

    public int deleteWorkOut(WorkOut workOut){

       String [] workOutIds = {workOut.getWorkOutId()};
       int count = mDatabases.delete(WorkOutsTabele.TABLE_WORKOUTS,
               WorkOutsTabele.COLUMN_WORKOUTID+" =?", workOutIds);
       return count;
   }

   public int deleteExercise(Exercise exercise){

        String [] exerciseIds = {exercise.getExerciseId()};
        int count = mDatabases.delete(ExercisesTable.TABLE_EXERCISES,
                ExercisesTable.COLUMN_ID+" =?", exerciseIds);

        mDatabases.delete(DisplaySetTable.TABLE_DISPLAY_SET,
                DisplaySetTable.COLUMN_EXERCISE_ID+" =?", exerciseIds);

        return count;
    }

    public int deleteDisplaySet(DisplaySet displaySet){

        String [] displaysets = {displaySet.getDisplaySetId()};
        int count = mDatabases.delete(DisplaySetTable.TABLE_DISPLAY_SET,
                DisplaySetTable.COLUMN_ID+" =?", displaysets);
        return count;
    }


}

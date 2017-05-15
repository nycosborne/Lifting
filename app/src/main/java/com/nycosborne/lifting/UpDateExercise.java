package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.DisplaySet;
import com.nycosborne.lifting.model.Exercise;

public class UpDateExercise extends AppCompatActivity {


    private EditText nameTv;
    private EditText wightTv;
    private EditText repsTv;
    private EditText setsTv;
    private Button updateBtn;


    private String name;
    private int wight;
    private int reps;
    private int sets;

    private DataSource mDataSource;
    private Exercise mExercise;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_exercise);

        intent = new Intent(this,LiftingActivity.class);
        mDataSource = new DataSource(this);

        mExercise = getIntent().getExtras().getParcelable("UpdateExercise");

        nameTv = (EditText) findViewById(R.id.updateexceriseName);
        wightTv = (EditText) findViewById(R.id.updateExercixeWight);
        repsTv = (EditText) findViewById(R.id.updateExerreps);
        setsTv = (EditText) findViewById(R.id.updatesets);

        updateBtn = (Button) findViewById(R.id.updateExcerButton);



    }


    public void upDateExerciseName(View view){
        name = nameTv.getText().toString();
        List<DisplaySet> displaySetList = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());

        for (DisplaySet displaySet:  displaySetList) {
            mDataSource.updateDisplaySetName(displaySet,name);
        }
        mDataSource.updateExerciseName(mExercise,name);

        startActivity(intent);
    }

    public void upDateExerciseWeight(View v){
        wight = Integer.parseInt(wightTv.getText().toString());
        List<DisplaySet> displaySetList = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());
        for (DisplaySet displaySet:  displaySetList) {
            mDataSource.updateDisplaySetWight(displaySet,wight);
        }
        mDataSource.updateExerciseWeight(mExercise,wight);
        startActivity(intent);
    }

    public void upDateExerciseReps(View view){
        reps = Integer.parseInt(repsTv.getText().toString());
        List<DisplaySet> displaySetList = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());
        for (DisplaySet displaySet:  displaySetList) {
            mDataSource.updateDisplaySetReps(displaySet,reps);
        }

        mDataSource.updateExerciseReps(mExercise,reps);
        startActivity(intent);
    }


    public void upDateExerciseSets(View view){
        sets = Integer.parseInt(setsTv.getText().toString());
        final int oldSets = mExercise.getSets();

        List<DisplaySet> displaySetList = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());
        for (DisplaySet displaySet : displaySetList) {
            mDataSource.updateDisplaySetSets(displaySet, sets);

        }
        mDataSource.updateExerciseSets(mExercise,sets);

        if (oldSets<sets) {
            int addDis = sets-oldSets;

            for (int i = 0; addDis > i; i++) {
                Log.d("addDis", "upDateExerciseSets: " + addDis);
                DisplaySet mDisplaySet = new DisplaySet(mExercise);
                mDisplaySet.setSets(sets);
                mDataSource.createDisplaySetItem(mDisplaySet);
            }
        }else if(oldSets>sets){
            int removeDis = oldSets-sets;
            for (int i = 0; removeDis> i; i++){
                mDataSource.deleteDisplaySet(displaySetList.get(i));
            }
        }


        startActivity(intent);
    }

    public void deleteExercise(View view){
        mDataSource.deleteExercise(mExercise);
        startActivity(intent);
    }

}

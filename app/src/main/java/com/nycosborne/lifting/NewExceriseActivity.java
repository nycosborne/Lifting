package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.DisplaySet;
import com.nycosborne.lifting.model.Exercise;
import com.nycosborne.lifting.model.WorkOut;

public class NewExceriseActivity extends AppCompatActivity {
    private WorkOut mWorkOut;
    private Exercise mExercise;

    public EditText exceriseNameInput;
    public EditText wightInput;
    public EditText repsInput;
    public EditText setsInput;
    private EditText restTimeInput;

    public String exceriseName;
    public int wight;
    public int reps;
    public int sets;
    private int restTime;
    private String mWorkOutId;
    private DataSource mDataSource;

    public static final String NEW_EXCERISE = "NEW_EXCERISE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_excerise);
        getSupportActionBar().setTitle("NewExceriseActivity");
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        exceriseNameInput = (EditText) findViewById(R.id.exceriseName);
        wightInput = (EditText) findViewById(R.id.wight);
        repsInput = (EditText) findViewById(R.id.reps);
        setsInput = (EditText) findViewById(R.id.sets);
        restTimeInput = (EditText) findViewById(R.id.timeET);
        mWorkOut = getIntent().getExtras().getParcelable("WorkOutToNewExercise");

        getSupportActionBar().setTitle("Add Exercise to " + mWorkOut.getWorkOutName());
        mDataSource = new DataSource(this);
    }

    public void saveExercise(View view){

        exceriseName = exceriseNameInput.getText().toString();
        wight = Integer.parseInt(wightInput.getText().toString());
        reps = Integer.parseInt(repsInput.getText().toString());
        sets = Integer.parseInt(setsInput.getText().toString());
        restTime = Integer.parseInt(restTimeInput.getText().toString());

        mWorkOutId = mWorkOut.getWorkOutId();
        mExercise = new Exercise(exceriseName,wight,reps,sets,restTime,mWorkOutId);

        mDataSource.createExercisesItem(mExercise);

        for (int i = 0; i < sets; i++){
            DisplaySet mDisplaySet = new DisplaySet(mExercise);
            mDataSource.createDisplaySetItem(mDisplaySet);


        }



        Intent intent = new Intent(this, WorkOutActivity.class);
        intent.putExtra("FromNewExcerise",mWorkOut);
        startActivity(intent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}

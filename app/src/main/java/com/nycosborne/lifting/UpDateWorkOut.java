package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.WorkOut;

public class UpDateWorkOut extends AppCompatActivity {

    private EditText workOutName;
    private EditText workOutDay;
    private Button saveWorkOutBtn;
    private Button deleteWorkOut;

    private String nameNew;
    private String dayNew;
    private String day = null;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    private Intent intent;
    private WorkOut mWorkOut;
    private DataSource mDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_work_out);

        intent = new Intent(this, MainActivity.class);

        mDataSource = new DataSource(this);
        mWorkOut = getIntent().getExtras().getParcelable("UpdateName");

        workOutName = (EditText)findViewById(R.id.updateworkOutName);
        workOutDay = (EditText)findViewById(R.id.updateworkOutDay);
        workOutDay.getText().toString();

        saveWorkOutBtn = (Button)findViewById(R.id.updatesaveWorkOutBtn);
        deleteWorkOut = (Button)findViewById(R.id.deleteWorkOut);

        spinner = (Spinner)findViewById(R.id.updatespinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.days,
                android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                day = (String) parent.getItemAtPosition(position);

                if (day.equals("Day_of_Work_Out")) {

                }else {
                    mDataSource.updateWorkOutDay(mWorkOut,day);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void upDateWorkOut(View view) {
        nameNew = workOutName.getText().toString();
        dayNew = workOutDay.getText().toString();


        if (!TextUtils.isEmpty(workOutName.getText()) && !TextUtils.isEmpty(workOutDay.getText())) {
            mDataSource.updateWorkOutNameAndDay(mWorkOut,nameNew,dayNew);
            mDataSource.close();
            startActivity(intent);
        }else if(!TextUtils.isEmpty(workOutName.getText())){
            mDataSource.updateWorkOutName(mWorkOut,nameNew);
            mDataSource.close();
            startActivity(intent);
        }else if(!TextUtils.isEmpty(workOutDay.getText())){
            mDataSource.updateWorkOutDay(mWorkOut,dayNew);
            mDataSource.close();
            startActivity(intent);
        }else {
            if (day != null) {
                mDataSource.updateWorkOutDay(mWorkOut,day);
                mDataSource.close();
                startActivity(intent);
            }

        }


    }

    public void deleteWorkOut(View view){
        mDataSource.deleteWorkOut(mWorkOut);
        mDataSource.close();
        startActivity(intent);
    }
}

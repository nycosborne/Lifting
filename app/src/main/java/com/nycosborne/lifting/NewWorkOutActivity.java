package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nycosborne.lifting.model.WorkOut;

public class NewWorkOutActivity extends AppCompatActivity {


    private WorkOut mWorkOut;


    private EditText workOutName;
    private EditText workOutDay;
    private String workOutNam;
    private String workOutId;
    private Button saveWorkOutBtn;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private boolean mIsSpinnerFirstCall=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work_out);
        workOutName = (EditText) findViewById(R.id.workOutName);
        workOutDay = (EditText) findViewById(R.id.workOutDay);
        saveWorkOutBtn = (Button) findViewById(R.id.saveWorkOutBtn);
        workOutNam = workOutName.getText().toString();
        workOutId = workOutDay.getText().toString();

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.days,
                android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                if (name.equals("Day of Work Out")) {

                }else {

                    workOutId = name;
                    Intent intent = new Intent();
                    workOutNam = workOutName.getText().toString();

                    if (workOutNam == null) {
                        mWorkOut = new WorkOut(workOutNam,workOutId);
                        intent.putExtra("NewWorkOut", mWorkOut);
                        setResult(RESULT_OK, intent);
                        finish();
                        Log.i("NewWorkOutActivity","Name is null");
                        Toast.makeText(NewWorkOutActivity.this, "Please Select a Day", Toast.LENGTH_LONG).show();

                    }else {
                        Log.i("NewWorkOutActivity","Name Not null");
                        mWorkOut = new WorkOut(workOutNam,workOutId);
                        intent.putExtra("NewWorkOut", mWorkOut);
                        setResult(RESULT_OK, intent);
                        finish();

                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void saveWorkOut(View view) {
        if (!TextUtils.isEmpty(workOutName.getText()) && !TextUtils.isEmpty(workOutDay.getText())) {

            workOutNam = workOutName.getText().toString();
            workOutId = workOutDay.getText().toString();
            mWorkOut = new WorkOut(workOutNam,workOutId);

            Intent intent = new Intent();

            intent.putExtra("NewWorkOut", mWorkOut);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
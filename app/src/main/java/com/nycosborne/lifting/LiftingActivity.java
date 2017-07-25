package com.nycosborne.lifting;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.nycosborne.lifting.adapter.DisplaySetAdapter;
import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.DisplaySet;
import com.nycosborne.lifting.model.Exercise;
import com.nycosborne.lifting.model.Results;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LiftingActivity extends AppCompatActivity implements DisplaySetAdapter.ItemClickCallback {
    private static final String TAG = "LIFTING_ACTIVITY";
    private Exercise mExercise;
    private DataSource mDataSource;
    public static  DisplaySet mDisplaySet;
    private DisplaySetAdapter adapter;

    private RecyclerView recyclerView;

    public AlertDialog dialog;

    private DisplaySet indDisplaySet;

    private EditText repsInput;
    private EditText wightInput;
    private Button saveInputBtn;
    private CountDownTimer countDownTimer = null;
    private MenuItem timy;
    private int mRestTime;

    private boolean toggle = true;
    private List<DisplaySet> displayList = new ArrayList<>();
    public static String displaySetId;
    public NotificationCompat.Builder notifyOBJ;
    private static final int uniuqID = 23463;
    private Date[] date;
    Calendar calendar1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifting);

        notifyOBJ = new NotificationCompat.Builder(this);
        notifyOBJ.setAutoCancel(true);

        mDataSource = new DataSource(this);
        try {
            List<Exercise> exerciseListt = mDataSource.getExercisesById(WorkOutActivity.exerciseSelected);
            mExercise = exerciseListt.get(0);



            assert mExercise != null;
            mRestTime  = mExercise.getRestTime();

            recyclerView = (RecyclerView)findViewById(R.id.LiftingRecyView);

            displayList = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());

            adapter = new DisplaySetAdapter(this,displayList);
            adapter.setItemClickCallback(this);
            recyclerView.setAdapter(adapter);
            getSupportActionBar().setTitle(mExercise.getExerciseName());


        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()

        Calendar calendar1 = new GregorianCalendar(2016,06,19);
        Calendar calendar2 = new GregorianCalendar(2016,06,20);
        Calendar calendar3 = new GregorianCalendar(2016,06,21);
        Calendar calendar4 = new GregorianCalendar(2016,06,26);
        Calendar calendar5 = new GregorianCalendar(2016,07,11);
        Calendar calendar6 = new GregorianCalendar(2016,07,13);
        Calendar calendar7 = new GregorianCalendar(2016,07,21);
        Calendar calendar8 = new GregorianCalendar(2016,07,23);

        Calendar calendar = Calendar.getInstance();

        ArrayList<Date> dates = new ArrayList<>();
        Date d1 = calendar1.getTime();
        Date d2 = calendar2.getTime();
        Date d3 = calendar3.getTime();
        Date d4 = calendar4.getTime();
        Date d5 = calendar5.getTime();
        Date d6 = calendar6.getTime();
        Date d7 = calendar7.getTime();
        Date d8 = calendar8.getTime();

        dates.add(d1);

        GraphView graph = (GraphView) findViewById(R.id.graph);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(getGraphData());



        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 4),
                new DataPoint(d2, 3),
                new DataPoint(d3, 4),
                new DataPoint(d4, 20),
                new DataPoint(d5, 1),
                new DataPoint(d6, 3),
                new DataPoint(d7, 4),
                new DataPoint(d8, 10),

        });


        graph.addSeries(series2);
        series2.setColor(Color.GREEN);
       // graph.addSeries(series);
// set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
//
        graph.getViewport().setMinX(date[0].getTime());
        graph.getViewport().setMaxX(date[date.length - 1].getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        for (int i = 0; i < date.length; i++) {
            Log.d("DateBuildTest", "getGraphData: "  + date[i]);
        }



        graph.getViewport().setMinY(1.0);
        graph.getViewport().setMaxY(150.0);
        graph.getViewport().setYAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary

        graph.getGridLabelRenderer().setHumanRounding(false);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private DataPoint[] getGraphData() {

        double wight = 0;
        double reps = 0;
        double set = 1;
        double avgWight = 0;
        double avgRegs = 0;

        ArrayList<String> dates = new ArrayList<String>();

        int count = 0;
        ArrayList<Double> arl =new ArrayList<>();
        List<Results> totalResultHolder;

            List<DisplaySet> displayIsCheck = mDataSource.getDisplaySetByExerciseId(mExercise.getExerciseId());

            String[] dateBind = new String[displayIsCheck.size()];
            int i = 0;
            for (DisplaySet displaySetItem: displayIsCheck) {

                dateBind[i] = displaySetItem.getDisplaySetId();
                i++;

            }
        totalResultHolder = mDataSource.getResultsDataPoint(dateBind);

        DataPoint[] values = new DataPoint[totalResultHolder.size()];
        date = new Date[totalResultHolder.size()];
        for (int ii=0; ii<totalResultHolder.size(); ii++) {
            String x = totalResultHolder.get(ii).getTimeStamp();

            String [] allDated = x.split("/");

            int year = Integer.parseInt(allDated[2]);
            int month = Integer.parseInt(allDated[0]);
            int day = Integer.parseInt(allDated[1]);

            calendar1 = new GregorianCalendar(year,1-month,day);

            //calendar1 = new GregorianCalendar(2016,07,01);


            date[ii] = calendar1.getTime();
            double y = totalResultHolder.get(ii).getWight();
            DataPoint v = new DataPoint(date[ii], totalResultHolder.get(ii).getWight());



            values[ii] =v;
        }
        for (int q = 0; q < date.length; q++) {
            Log.d("DateBuildTest", "getGraphData: Other "  + date[q]);
        }
        return values;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lifting,menu);
        timy = menu.findItem(R.id.timer);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.updateExer:
                Intent intent = new Intent(this,UpDateExercise.class);
                intent.putExtra("UpdateExercise", mExercise);
                startActivity(intent);
                return true;
            case R.id.exercisesComplete:
                exerciseProgress();
                return true;
            case R.id.timer:
                if (countDownTimer == null) {
                    startTimer();
                }else {
                    cancelTimer();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startTimer(){
        timy.setTitle("Clicked");
         countDownTimer = new CountDownTimer(mRestTime * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timy.setIcon(null);
                timy.setTitle(" " + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                timy.setTitle("Done!!");
                timerAlert();
            }
         };
        countDownTimer.start();
    }

    private void cancelTimer(){
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
            timy.setIcon(getResources().getDrawable(R.drawable.ic_alarm_black_24dp));
        }
    }

    @Override
    public void onItemClick(int p) {
        enterResults(p);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        enterResults(p);
    }

    public void upUpDateDisplaySetList(String exceriseId, int p){
        displayList.clear();
        DataSource mDataSource2 = new DataSource(this);
        displayList = mDataSource2.getDisplaySetByExerciseId(exceriseId);
        mDataSource2.close();
        adapter.setListDate(displayList);
        adapter.notifyItemChanged(p);
        mDataSource2.close();
    }

    public void timerAlert(){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notifyOBJ.setSound(alarmSound);
        notifyOBJ.setSmallIcon(R.mipmap.lifitng_icon);
        notifyOBJ.setTicker("Timer Done!!");
        notifyOBJ.setContentTitle("Timer Done!! Start your next set");
        notifyOBJ.setWhen(System.currentTimeMillis());

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifyOBJ.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniuqID, notifyOBJ.build());
    }

    public void enterResults(final int p){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(LiftingActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_enter_results, null);

        repsInput = (EditText) mView.findViewById(R.id.repsInput);
        wightInput = (EditText) mView.findViewById(R.id.wightInput);

        DisplaySet item3 = displayList.get(p);

        repsInput.setText(Integer.toString(item3.getReps()));
        wightInput.setText(Integer.toString(item3.getWight()));
        mBuilder.setNegativeButton("Skip Entry",null);
        mBuilder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override


            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(repsInput.getText())
                        && (!TextUtils.isEmpty(wightInput.getText()))) {

                    int reps = Integer.parseInt(repsInput.getText().toString());
                    int wight = Integer.parseInt(wightInput.getText().toString());

                    Results results = new Results(reps, wight, displaySetId);
                    mDataSource.createResultsItem(results);
                    List<DisplaySet> item = mDataSource.getDisplaySetByDisplaySetId(displaySetId);
                    repsInput.getText().clear();
                    wightInput.getText().clear();

                    DisplaySet item2 = displayList.get(p);
                    if (item2.getIsChecked()== 0) {
                        mDataSource.upDateDisplaySetCheck(item2, 1);
                    }
                    upUpDateDisplaySetList(mExercise.getExerciseId(), p);

                    if (isAllCheck()) {
                        workOutComplete();
                    }
                }else {
                    // TODO: 5/8/17 replace toast with AlertDialog
                    Toast.makeText(getApplicationContext(), "Please Enter Both Wight and Reps",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        dialog.setCancelable(false);

    }

    public void exerciseProgress(){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(LiftingActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_exercise_pregress, null);

        mBuilder.setNegativeButton("OK",null);
        int totleWight = 0;
        int totleReps = 0;
        int unLiftedWight = 0;
        int unLiftedResp = 0;


        List<Exercise> allExercises = mDataSource.getExercisesByWorkOutId(mExercise.getWorkOutId());
        for (Exercise ExerciseItem: allExercises ) {

            List<DisplaySet> displayIsCheck = mDataSource.getDisplaySetByExerciseId(ExerciseItem.getExerciseId());
            for (DisplaySet displaySetItem: displayIsCheck) {
                if(displaySetItem.getIsChecked()==0){
                    unLiftedWight += displaySetItem.getWight();
                }
                List<Results> totalResults = mDataSource.getResultsByDisplaySetId(displaySetItem.getDisplaySetId());
               if (displaySetItem.getIsChecked()==1){
                   totleWight += totalResults.get(0).getWight();
                   totleReps += totalResults.get(0).getReps();

               }
            }
        }

        String wightToDisplay = Integer.toString(totleWight);
        String repsToDisplay = Integer.toString(totleReps);

        String displayString = "You've Lifted\n" +
                wightToDisplay +"lb over " + repsToDisplay + " Reps"+
                "\nYou have " + unLiftedWight + "lb left to lift";

        TextView showResultsDialog = (TextView)mView.findViewById(R.id.dailogPrgressResults);
        showResultsDialog.setText(displayString);


        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        //dialog_enter_results.getWindow().setLayout(300,300);
        dialog.setCancelable(false);


    }

    public void workOutComplete(){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(LiftingActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_workout_complet, null);

        String displayString = "";
        int totleWight = 0;
        int totleReps = 0;

        List<Exercise> allExercises = mDataSource.getExercisesByWorkOutId(mExercise.getWorkOutId());
            for (Exercise ExerciseItem: allExercises ) {

                List<DisplaySet> displayIsCheck = mDataSource.getDisplaySetByExerciseId(ExerciseItem.getExerciseId());
                for (DisplaySet displaySetItem: displayIsCheck) {

                    List<Results> totalResults = mDataSource.getResultsByDisplaySetId(displaySetItem.getDisplaySetId());

                    // TODO: 5/12/17 bug issue here
                    try {
                        totleWight += totalResults.get(0).getWight();
                        totleReps += totalResults.get(0).getReps();
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
            }

        displayString = displayString.concat("\nYou lifted "+ totleWight +
        "lb over " + totleReps + " Reps");

        TextView showResultsDialog = (TextView)mView.findViewById(R.id.showResultsTV);
        showResultsDialog.setText(displayString);

        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //isAllCheck() or
                List<Exercise> allExercises = mDataSource.getExercisesByWorkOutId(mExercise.getWorkOutId());
                for (Exercise ExerciseItem: allExercises ) {
                    List<DisplaySet> displayIsCheck = mDataSource.getDisplaySetByExerciseId(ExerciseItem.getExerciseId());
                    for (DisplaySet displaySetItem: displayIsCheck) {
                        if(displaySetItem.getIsChecked() == 1){
                            mDataSource.upDateDisplaySetCheck(displaySetItem, 0);
                        }
                    }
                }
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        dialog.setCancelable(false);

    }

    public boolean isAllCheck(){

        boolean allCheck = true;
        DataSource dbCheckUpdate = new DataSource(this);

        List<Exercise> allExercises = dbCheckUpdate.getExercisesByWorkOutId(mExercise.getWorkOutId());
        for (Exercise ExerciseItem: allExercises ) {
            List<DisplaySet> displayIsCheck = dbCheckUpdate.getDisplaySetByExerciseId(ExerciseItem.getExerciseId());
            for (DisplaySet displaySetItem: displayIsCheck) {
                Log.d(TAG, "isAllCheck: " + displaySetItem.getIsChecked());
                if (displaySetItem.getIsChecked() == 0){
                    allCheck = false;
                }
            }
        }
        dbCheckUpdate.close();
        return allCheck;
    }

    public void unCheckAll(View view){
        List<Exercise> allExercises = mDataSource.getExercisesByWorkOutId(mExercise.getWorkOutId());
        for (Exercise ExerciseItem: allExercises ) {
            List<DisplaySet> displayIsCheck = mDataSource.getDisplaySetByExerciseId(ExerciseItem.getExerciseId());
            for (DisplaySet displaySetItem: displayIsCheck) {
               if(displaySetItem.getIsChecked() == 1){
                   mDataSource.upDateDisplaySetCheck(displaySetItem, 0);
               }
            }
        }

    }
}

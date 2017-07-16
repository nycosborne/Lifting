package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.nycosborne.lifting.adapter.ExceriseAdapter;
import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.Exercise;
import com.nycosborne.lifting.model.WorkOut;

import java.util.ArrayList;
import java.util.List;

public class WorkOutActivity extends AppCompatActivity {
    private static final String WORKOUT_ACTIVITY= "WorkOut_Activity";
    public static final String WORK_OUT = "WORK_OUT";
    private WorkOut mWorkOut;

    private DataSource mDataSource;
    private ExceriseAdapter adapter;
    public List<Exercise> exerciseslist = new ArrayList<>();
    public static String exerciseSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);
        mDataSource = new DataSource(this);

        exerciseslist = mDataSource.getExercisesByWorkOutId(MainActivity.listId);
        List<WorkOut> workoutt = mDataSource.getWorkOutById(MainActivity.listId);
        mWorkOut = workoutt.get(0);
        adapter = new ExceriseAdapter(this,exerciseslist);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.workOutRcy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        getSupportActionBar().setTitle(mWorkOut.getWorkOutName());

    }
    private ItemTouchHelper.Callback helperCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {

                        moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        swipeDeleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleCallback;
    }
    private void moveItem(int oldPosition, int newPosition){

        Exercise item = (Exercise) exerciseslist.get(oldPosition);
        exerciseslist.remove(oldPosition);
        exerciseslist.add(newPosition, item);
        adapter.notifyItemMoved(oldPosition,newPosition);
    }

    private void swipeDeleteItem(final int position){
        mDataSource.deleteExercise(exerciseslist.get(position));
        exerciseslist.remove(position);
        adapter.notifyItemRemoved(position);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_workout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newExercise:
                Intent intent = new Intent(this, NewExceriseActivity.class);
                intent.putExtra("WorkOutToNewExercise",mWorkOut);

                startActivity(intent);
                return true;
            case R.id.updateName:
                Intent updateintent = new Intent(this, UpDateWorkOut.class);

                updateintent.putExtra("UpdateName", mWorkOut);
                startActivity(updateintent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(WORK_OUT,mWorkOut);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.nycosborne.lifting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.nycosborne.lifting.adapter.WorkOutAdapter;
import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.WorkOut;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private List<WorkOut> workoutlist = new ArrayList<>();

    private WorkOut mWorkOut;
    private  Intent intent;
    public  RecyclerView recyclerView;
    private WorkOutAdapter adapter;

    public static String listId ="";

    private DataSource mDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  getSupportActionBar().setTitle("");

        intent = new Intent(this, NewWorkOutActivity.class);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,1);

            }
        });


        mDataSource = new DataSource(this);
        mDataSource.open();


        workoutlist = mDataSource.getAllWorkOuts(null);
        adapter = new WorkOutAdapter(this,workoutlist);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvItem);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
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

        WorkOut item = (WorkOut) workoutlist.get(oldPosition);
        workoutlist.remove(oldPosition);
        workoutlist.add(newPosition, item);
        adapter.notifyItemMoved(oldPosition,newPosition);
    }

    private void swipeDeleteItem(final int position){
        mDataSource.deleteWorkOut(workoutlist.get(position));
        workoutlist.remove(position);
        adapter.notifyItemRemoved(position);


    }

    // TODO: 4/19/17 Need to imporve backBtn Fuctioality from MainAvativiy
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK)
        {
            //mDataSource.open();
            //  mWorkOut = data.getExtras("Data");
            mWorkOut = data.getExtras().getParcelable("NewWorkOut");

            mDataSource.createWorkOutItem(mWorkOut);
            workoutlist = mDataSource.getAllWorkOuts(null);
            adapter = new WorkOutAdapter(this,workoutlist);
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvItem);
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newWorkOut:
                startActivityForResult(intent,1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



// TODO: 4/26/17 need to fully implent
    //    @Override
//    protected void onPause() {
//        super.onPause();
//        mDataSource.close();
//    }

}

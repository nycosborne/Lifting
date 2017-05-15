package com.nycosborne.lifting.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.nycosborne.lifting.LiftingActivity;
import com.nycosborne.lifting.R;
import com.nycosborne.lifting.WorkOutActivity;
import com.nycosborne.lifting.model.Exercise;

/**
 * Created by danielosborne on 4/15/17.
 */

public class ExceriseAdapter extends RecyclerView.Adapter<ExceriseAdapter.ViewHolder>{

    public static final String EXCERISE_ITEM_ADAPTER = "EXCERISE_ITEM_ADAPTER";
    private List<Exercise> mItems;
    private Context mContext;

    public ExceriseAdapter(Context context, List<Exercise> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ExceriseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.excerise_adapter_card, parent, false);
        ExceriseAdapter.ViewHolder viewHolder = new ExceriseAdapter.ViewHolder(itemView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ExceriseAdapter.ViewHolder holder, int position) {
        final Exercise item = mItems.get(position);

        Log.i(EXCERISE_ITEM_ADAPTER," adapter love");
        holder.tvName.setText(item.getExerciseName());
        int set = item.getSets();
        int wight = item.getWight();
        int reps = item.getReps();
        holder.tvDec.setText(set+" sets of "+ reps +"resps " + wight + " lb" );

        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, LiftingActivity.class);
                intent.putExtra("FromExceriseAdapter",item);
                WorkOutActivity.exerciseSelected = item.getExerciseId();
                mContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvDec;
        public int set;
        public int wight;
        public int reps;


        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.itemNameTextforExerciceAdapter);
            tvDec = (TextView) itemView.findViewById(R.id.workoutDecption);
            mView = itemView;
        }
    }
}

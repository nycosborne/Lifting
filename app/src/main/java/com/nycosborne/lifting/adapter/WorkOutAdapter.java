package com.nycosborne.lifting.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.nycosborne.lifting.MainActivity;
import com.nycosborne.lifting.R;
import com.nycosborne.lifting.WorkOutActivity;
import com.nycosborne.lifting.model.WorkOut;

/**
 * Created by danielosborne on 4/14/17.
 */

public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.ViewHolder> {

    private List<WorkOut> mItems;
    private Context mContext;


    public WorkOutAdapter(Context context, List<WorkOut> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public WorkOutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.workout_adapter_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(WorkOutAdapter.ViewHolder holder, int position) {
        final WorkOut item = mItems.get(position);

        holder.tvName.setText(item.getWorkOutName());
        holder.tvDay.setText(item.getWorkOutDay());

        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, WorkOutActivity.class);
                intent.putExtra("FromAdapterToWorkOutActivity", item);
                MainActivity.listId = item.getWorkOutId();
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
        public TextView tvDay;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.itemNameText);
            tvDay = (TextView) itemView.findViewById(R.id.itemDayText);
            mView = itemView;
        }
    }


}


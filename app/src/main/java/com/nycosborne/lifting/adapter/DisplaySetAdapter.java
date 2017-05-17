package com.nycosborne.lifting.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nycosborne.lifting.LiftingActivity;
import com.nycosborne.lifting.R;
import com.nycosborne.lifting.database.DataSource;
import com.nycosborne.lifting.model.DisplaySet;
import com.nycosborne.lifting.model.Results;

import java.util.List;

/**
 * Created by danielosborne on 4/19/17.
 */
// TODO: 4/21/17 Need to consder replacing this this a adapter that just handel Exercise obj

public class DisplaySetAdapter extends RecyclerView.Adapter<DisplaySetAdapter.ViewHolder>{



    private List<DisplaySet> mItems;
    private Context mContext;
    int evenOdd = 1;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }
    public DisplaySetAdapter(Context context, List<DisplaySet> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public DisplaySetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.display_set_adapter_item, parent, false);
        DisplaySetAdapter.ViewHolder viewHolder = new DisplaySetAdapter.ViewHolder(itemView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(DisplaySetAdapter.ViewHolder holder, int position) {
        final DisplaySet item = mItems.get(position);

        DataSource mDataSource = new DataSource(mContext);

        List<Results> list = mDataSource.getResultsByDisplaySetId(item.getDisplaySetId());

        if(item.getIsChecked() == 0){
           holder.imageV.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }else {
            holder.imageV.setImageResource(R.drawable.ic_check_box_black_24dp);
        }

        holder.tvName.setText(item.getExerciseName());
        int set = item.getSets();
        int wight = item.getWight();
        int reps = item.getReps();
        if ((position % 2) != 0) {
            holder.constraintLayout.setBackgroundColor(Color.rgb(188, 37, 37));
        }else {
            holder.constraintLayout.setBackgroundColor(Color.rgb(155, 32, 32));
        }
        evenOdd++;
        holder.tvDec.setText(reps +"reps " + wight + "lb" );


        if (list.size()==1){

            int oldReps = list.get(0).getReps();
            int oldWight = list.get(0).getWight();
            String dateTime = list.get(0).getTimeStamp();

            String [] splitDateTime = dateTime.split("\\s");

            holder.tvOldReps1.setText(splitDateTime[0] +" "+ oldReps +"X"+oldWight+"lb");

        }else if (list.size()==2){
            int oldReps = list.get(0).getReps();
            int oldWight = list.get(0).getWight();
            String dateTime = list.get(0).getTimeStamp();
            String [] splitDateTime = dateTime.split("\\s");

            int olderReps = list.get(1).getReps();
            int olderWight = list.get(1).getWight();
            String daterTime = list.get(1).getTimeStamp();
            String [] splitDaterTime = daterTime.split("\\s");


            holder.tvOldReps1.setText(splitDateTime[0] +" "+ oldReps +"X"+oldWight+"lb");
            holder.tvOldReps2.setText(splitDaterTime[0] +" "+ olderReps +"X"+olderWight+"lb");
        }else if (list.size()>=3){
            int oldReps = list.get(0).getReps();
            int oldWight = list.get(0).getWight();
            String dateTime = list.get(0).getTimeStamp();
            String [] splitDateTime = dateTime.split("\\s");

            int olderReps = list.get(1).getReps();
            int olderWight = list.get(1).getWight();
            String daterTime = list.get(1).getTimeStamp();
            String [] splitDaterTime = daterTime.split("\\s");

            int oldestReps  = list.get(2).getReps();
            int oldestWight = list.get(2).getWight();
            String datestTime = list.get(2).getTimeStamp();
            String [] splitDatestTime = datestTime.split("\\s");


            holder.tvOldReps1.setText(splitDateTime[0] +" "+ oldReps +"X"+oldWight+"lb");
            holder.tvOldReps2.setText(splitDaterTime[0] +" "+ olderReps +"X"+olderWight+"lb");
            holder.tvOldReps3.setText(splitDatestTime[0] +" "+ oldestReps +"X"+oldestWight+"lb");
        }

    }
    public void setListDate(List<DisplaySet> exceriseList){
        this.mItems.clear();
        this.mItems.addAll(exceriseList);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvName;
        public TextView tvDec;
        public TextView tvOldReps1;
        public TextView tvOldReps2;
        public TextView tvOldReps3;
        private ImageView imageV;
        private View contaner;
        private ConstraintLayout constraintLayout;

        public View mView;

        public ViewHolder(View itemView) {

            super(itemView);
            imageV = (ImageView) itemView.findViewById(R.id.checkbox);
            tvName = (TextView) itemView.findViewById(R.id.itemNameTextforDisplaySetAdapter);
            tvDec = (TextView) itemView.findViewById(R.id.exceriseDecption);
            tvOldReps1 = (TextView) itemView.findViewById(R.id.oldRepstextView1);
            tvOldReps2 = (TextView) itemView.findViewById(R.id.oldRepstextView2);
            tvOldReps3 = (TextView) itemView.findViewById(R.id.oldRepstextView3);
            contaner = itemView.findViewById(R.id.displaySet_item_root);
            constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.displaySet_item_root_style_Leyer);

            imageV.setOnClickListener(this);
            contaner.setOnClickListener(this);

            mView = itemView;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.displaySet_item_root){
                try{

                    itemClickCallback.onItemClick(getAdapterPosition());
                    LiftingActivity.displaySetId = mItems.get(getAdapterPosition()).getDisplaySetId();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                }else {
                try{
                    itemClickCallback.onSecondaryIconClick(getAdapterPosition());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

        }
    }
}

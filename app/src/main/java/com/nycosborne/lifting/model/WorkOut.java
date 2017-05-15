package com.nycosborne.lifting.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nycosborne.lifting.database.WorkOutsTabele;

/**
 * Created by danielosborne on 4/13/17.
 */

public class WorkOut implements Parcelable {
    private String workOutId;
    private String workOutName;
    private String workOutDay;
    private int sortPosition;
    private List<Exercise> exercises;

    public WorkOut(){

    }

    public WorkOut(String workOutId, String workOutName, String workOutDay,
                   int sortPosition, List<Exercise> exercises) {

       if(workOutId == null){
           workOutId = UUID.randomUUID().toString();
       }

        this.workOutId = workOutId;
        this.workOutName = workOutName;
        this.workOutDay = workOutDay;
        this.sortPosition = sortPosition;
        this.exercises = exercises;
    }

    public WorkOut(String workOutName, String workOutday ){

        if(workOutId == null){
            workOutId = UUID.randomUUID().toString();
        }

        this.workOutName = workOutName;
        this.workOutDay = workOutday;
    }


//    public void addExercise(String name, int wight, int reps, int sets, String workOutId){
//        exercises.add(new Exercise(name,wight,reps,sets, workOutId));
//    }


    public String getWorkOutId() {
        return workOutId;
    }

    public void setWorkOutId(String workOutId) {
        this.workOutId = workOutId;
    }

    public String getWorkOutName() {
        return workOutName;
    }

    public void setWorkOutName(String workOutName) {
        this.workOutName = workOutName;
    }

    public String getWorkOutDay() {
        return workOutDay;
    }

    public void setWorkOutDay(String workOutDay) {
        this.workOutDay = workOutDay;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }



    public ContentValues toValue() {
        ContentValues values = new ContentValues(4);
        values.put(WorkOutsTabele.COLUMN_WORKOUTID, workOutId);
        values.put(WorkOutsTabele.COLUMN_NAME, workOutName);
        values.put(WorkOutsTabele.COLUMN_DAY, workOutDay);
        values.put(WorkOutsTabele.COLUMN_POSITION, sortPosition);


        return values;

    }


    @Override
    public String toString() {
        return "WorkOut{" +
                "workOutId='" + workOutId + '\'' +
                ", workOutName='" + workOutName + '\'' +
                ", workOutDay='" + workOutDay + '\'' +
                ", sortPosition=" + sortPosition +
                ", exercises=" + exercises +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.workOutId);
        dest.writeString(this.workOutName);
        dest.writeString(this.workOutDay);
        dest.writeInt(this.sortPosition);
        dest.writeList(this.exercises);
    }

    protected WorkOut(Parcel in) {
        this.workOutId = in.readString();
        this.workOutName = in.readString();
        this.workOutDay = in.readString();
        this.sortPosition = in.readInt();
        this.exercises = new ArrayList<Exercise>();
        in.readList(this.exercises, Exercise.class.getClassLoader());
    }

    public static final Parcelable.Creator<WorkOut> CREATOR = new Parcelable.Creator<WorkOut>() {
        @Override
        public WorkOut createFromParcel(Parcel source) {
            return new WorkOut(source);
        }

        @Override
        public WorkOut[] newArray(int size) {
            return new WorkOut[size];
        }
    };
}

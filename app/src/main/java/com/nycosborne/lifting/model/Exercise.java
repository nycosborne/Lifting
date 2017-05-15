package com.nycosborne.lifting.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import com.nycosborne.lifting.database.ExercisesTable;

/**
 * Created by danielosborne on 4/13/17.
 */

public class Exercise implements Parcelable {
    private String exerciseId;
    private String exerciseName;
    private int wight;
    private int reps;
    private int sets;
    private int sortPosition;
    private int restTime;
    private String workOutId;


    public Exercise() {
    }

    public Exercise(String exerciseName, int wight, int reps, int sets, int restTime, String workOutId) {

        if(exerciseId == null){
            exerciseId = UUID.randomUUID().toString();
        }

        this.exerciseName = exerciseName;
        this.wight = wight;
        this.reps = reps;
        this.sets = sets;
        this.restTime = restTime;
        this.workOutId = workOutId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getWorkOutId() {
        return workOutId;
    }

    public void setWorkOutId(String workOutId) {
        this.workOutId = workOutId;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public ContentValues toValue() {
        ContentValues values = new ContentValues(8);
        values.put(ExercisesTable.COLUMN_ID, exerciseId);
        values.put(ExercisesTable.COLUMN_NAME, exerciseName);
        values.put(ExercisesTable.COLUMN_WIGHT, wight);
        values.put(ExercisesTable.COLUMN_REPS, reps);
        values.put(ExercisesTable.COLUMN_SETS, sets);
        values.put(ExercisesTable.COLUMN_POSITION, sortPosition);
        values.put(ExercisesTable.COLUMN_REST_TIME, restTime);
        values.put(ExercisesTable.COLUMN_WORKOUTID, workOutId);

        return values;

    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseId='" + exerciseId + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", wight=" + wight +
                ", reps=" + reps +
                ", sets=" + sets +
                ", sortPosition=" + sortPosition +
                ", restTime=" + restTime +
                ", workOutId='" + workOutId + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.exerciseId);
        dest.writeString(this.exerciseName);
        dest.writeInt(this.wight);
        dest.writeInt(this.reps);
        dest.writeInt(this.sets);
        dest.writeInt(this.sortPosition);
        dest.writeInt(this.restTime);
        dest.writeString(this.workOutId);
    }

    protected Exercise(Parcel in) {
        this.exerciseId = in.readString();
        this.exerciseName = in.readString();
        this.wight = in.readInt();
        this.reps = in.readInt();
        this.sets = in.readInt();
        this.sortPosition = in.readInt();
        this.restTime = in.readInt();
        this.workOutId = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel source) {
            return new Exercise(source);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}


package com.novytes.fuzzz.app.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by res on 20/10/14.
 */
public class TargetInfo implements Parcelable{

    private String appName, targetActivity;
    private int type;
    private String successActivity, failureActivity, toastText;

    public TargetInfo(){
        this.appName = "";
        this.targetActivity = "";
        this.type = -1;
        this.failureActivity = "";
        this.successActivity = "";
        this.toastText = "";
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTargetActivity() {
        return targetActivity;
    }

    public void setTargetActivity(String targetActivity) {
        this.targetActivity = targetActivity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSuccessActivity() {
        return successActivity;
    }

    public void setSuccessActivity(String successActivity) {
        this.successActivity = successActivity;
    }

    public String getFailureActivity() {
        return failureActivity;
    }

    public void setFailureActivity(String failureActivity) {
        this.failureActivity = failureActivity;
    }

    public String getToastText() {
        return toastText;
    }

    public void setToastText(String toastText) {
        this.toastText = toastText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(appName);
        parcel.writeString(targetActivity);
        parcel.writeInt(type);
        parcel.writeString(successActivity);
        parcel.writeString(failureActivity);
        parcel.writeString(toastText);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TargetInfo createFromParcel(Parcel in) {
            return new TargetInfo(in);
        }

        @Override
        public TargetInfo[] newArray(int i) {
            return new TargetInfo[i];
        }
    };
    private TargetInfo(Parcel in) {
        appName = in.readString();
        targetActivity = in.readString();
        type = in.readInt();
        successActivity = in.readString();
        failureActivity = in.readString();
        toastText = in.readString();
    }

}

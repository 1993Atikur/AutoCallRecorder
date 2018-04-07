package andro.geeks.pack.autocallrecorder.Recycleerview;

import android.os.Parcel;

/**
 * Created by pallob on 4/2/18.
 */

public class Callers  {
    String name;
    String number;
    String date;
    String duration;
    String fileName;


    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

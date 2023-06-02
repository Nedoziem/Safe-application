package com.example.safeapp;
import static androidx.room.ForeignKey.CASCADE;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


public class Incident {
    private int id;
    private double latitude;
    private double longtitude;
    private String text;
    private String date;

    public Incident(){

    }
    public Incident(double latitude, double longtitude, String text, String date){
        id++;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.text = text;
        this.date = date;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongtitude(){
        return this.latitude;
    }

    public String getText(){
        return text;
    }

    public String getDate(){
        return date;
    }

    public String toString(){
        return "Incident" + this.id + "id," + "location" + this.latitude + this.longtitude + this.text + "incident date:" + this.date;
    }



}

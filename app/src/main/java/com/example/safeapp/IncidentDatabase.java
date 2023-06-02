package com.example.safeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class IncidentDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Incident.db";

    public IncidentDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final class IncidentTable {
        public static final String TABLE_NAME = "locations";
        public static final String COLUMN_NAME_ID = "uniqueID";
        public static final String COLUMN_NAME_LATCOORD = "latcoord";
        public static final String COLUMN_NAME_LONGCOORD = "longcoord";
        public static final String COLUMN_NAME_DESC = "description";

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //it should be db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL("CREATE TABLE " + IncidentTable.TABLE_NAME + " ( " +
                IncidentTable.COLUMN_NAME_ID + "INTEGER PRIMARY KEY," + IncidentTable.COLUMN_NAME_LATCOORD + "FLOAT, " + IncidentTable.COLUMN_NAME_LONGCOORD +
                "FLOAT, " + IncidentTable.COLUMN_NAME_DESC + "TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + IncidentTable.TABLE_NAME);
        onCreate(db);
    }

    //might take out soon
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addIncident() {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(IncidentTable.COLUMN_NAME_ID, 00001);
        values.put(IncidentTable.COLUMN_NAME_LATCOORD, 28.524209f);
        values.put(IncidentTable.COLUMN_NAME_LONGCOORD, -81.369673f);
        values.put(IncidentTable.COLUMN_NAME_DESC, "This contains information about orlando");


        //values.put(IncidentTable.COLUMN_NAME_ID, 00001);
        values.put(IncidentTable.COLUMN_NAME_LATCOORD, 28.524209f);
        values.put(IncidentTable.COLUMN_NAME_LONGCOORD, -81.369673f);
        values.put(IncidentTable.COLUMN_NAME_DESC, "This contains information about orlando");

        long incidentId = db.insert(IncidentTable.TABLE_NAME, null, values);
        return incidentId;
    }

}

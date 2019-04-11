package com.example.happyhealthyme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "biometrics.db";
    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + HappyHealthyMeDbSchema.BiometricsTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                HappyHealthyMeDbSchema.BiometricsTable.Cols.UUID + ", " +
                HappyHealthyMeDbSchema.BiometricsTable.Cols.DATE + ", " +
                HappyHealthyMeDbSchema.BiometricsTable.Cols.HEIGHT + ", " +
                HappyHealthyMeDbSchema.BiometricsTable.Cols.WEIGHT + ", " +
                HappyHealthyMeDbSchema.BiometricsTable.Cols.SLEEP + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

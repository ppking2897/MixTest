package com.example.shiningtechw.mixtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MySQLiteHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "imageInfo";
    private String PRIMARY_KEY = "_id";
    private String BRAND = "brand";
    private String PICTURE_LOCATION = "location";
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "
                + TABLE_NAME+ " ("
                + PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "  //這個屬性可以讓每次新增一筆資料，自動累加
                + BRAND + " VARCHAR, "
                + PICTURE_LOCATION + " VARCHAR);";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertInfo(SQLiteDatabase db , String brand , String location){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BRAND , brand);
        contentValues.put(PICTURE_LOCATION , String.valueOf(location));

        db.insert(TABLE_NAME , null , contentValues);
    }

    public Cursor getAllData(SQLiteDatabase database){
        return database.query(TABLE_NAME
                , new String[]{PRIMARY_KEY , BRAND , PICTURE_LOCATION}
                , null , null , null ,null , null);
    }
}

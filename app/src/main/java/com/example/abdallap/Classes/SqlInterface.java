package com.example.abdallap.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface SqlInterface {

     long Add(SQLiteDatabase db);
     int Delete(SQLiteDatabase db, int id);
     int Update(SQLiteDatabase db, int id);

    int Delete(SQLiteDatabase db, String id);

    int Update(SQLiteDatabase db, String id);

    Cursor Select(SQLiteDatabase db);
}

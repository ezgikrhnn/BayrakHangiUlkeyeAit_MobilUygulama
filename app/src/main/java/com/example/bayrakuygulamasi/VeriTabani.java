package com.example.bayrakuygulamasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeriTabani extends SQLiteOpenHelper {


    public VeriTabani(@Nullable Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS 'bayraklar' (\n" +
                "\t 'bayrak_id'\t INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t 'bayrak_ad'\tTEXT,\n" +
                "\t 'bayrak_resim'\tTEXT\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bayraklar");
        onCreate(db);
    }
}

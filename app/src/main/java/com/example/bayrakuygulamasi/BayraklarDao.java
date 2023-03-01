package com.example.bayrakuygulamasi;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//veri tabanından kodları çekmek için bir dao sınıfı oluşturuyorum
public class BayraklarDao {


    public ArrayList<Bayraklar> rastgele5Getir(VeriTabani vt){
      ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();
      
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5", null);

        while (c.moveToNext()){ //diyerek kaydetmeye başlayacağım
            @SuppressLint("Range") Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    , c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(b);
        }
        return bayraklarArrayList;   //ben bu metodu çalıştırdığım zaman bana 5 tane rastgele bayrak getirecek
    }

    //şimdi rastgele 3 yanlış secenek getirme için sorfular yazıyorum
    public ArrayList<Bayraklar> rastgele3YanlisSecenekGetir(VeriTabani vt, int bayrak_id){
        ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != "+bayrak_id+" ORDER BY RANDOM() LIMIT 5", null);
        //burada şu denilmek isteniyor:
        //benim doğru bayraklarımın idsine eşit olmayan 3 tane yanlış id getir.

        while (c.moveToNext()){ //diyerek kaydetmeye başlayacağım
            @SuppressLint("Range") Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    , c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(b);
        }
        return bayraklarArrayList;   //ben bu metodu çalıştırdığım zaman bana 5 tane rastgele bayrak getirecek
    }
}


package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewDogru, textViewYanlis, textViewSoruSayi;
    private ImageView imageViewBayrak;
    private Button buttonA, buttonB, buttonC, buttonD;

    //5 soruluk bir sınav yapacağım için 5 soruyu getirecek arraylist tanımlıyorum:
    private ArrayList<Bayraklar> sorularListe;

    //yanlış seceneklerı getirecek arraylisti tanımlıyorum:
    private ArrayList<Bayraklar> yanlisSeceneklerListe;

    //anlık olarak kullanacağım doğru soru nesnesini tanimliyorum.
    private Bayraklar dogruSoru;

    private VeriTabani vt;

    //sayaçlarımı tanımlıyorum, 3 tane sayacım olmalı:
    private int soruSayac =0;
    private int yanlisSayac =0;
    private int dogruSayac=0;

    //sorunun seceneklerini karıştırabilmem lazım
    private HashSet<Bayraklar> secenekleriKaristirmaListe;
    private ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayi = findViewById(R.id.textViewSoruSayi);
        imageViewBayrak = findViewById(R.id.imageViewBayrak);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);


        vt = new VeriTabani(this);
        sorularListe = new BayraklarDao().rastgele5Getir(vt); //diyerek sorularımızı alalım

        soruYukle();



        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // öncelikle doğruluk kontrolu yapacak:
                dogruKontrol(buttonA);
                //sayac kontrolunu yapıyorum:
                sayacKontrol();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                sayacKontrol();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // öncelikle doğruluk kontrolu yapacak:
                dogruKontrol(buttonC);
                //sayac kontrolunu yapıyorum:
                sayacKontrol();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //öncelikle doğruluk kontrolu yapacak:
                dogruKontrol(buttonD);
                //sayac kontrolunu yapıyorum:
                sayacKontrol();
            }
        });

    }

    public void soruYukle(){
        //soru yüklenir yüklenmez soru sayacımı görebilmem gerekiyor.
        textViewSoruSayi.setText((soruSayac+1)+ ". SORU");
        textViewDogru.setText("DOĞRU: " +dogruSayac);
        textViewYanlis.setText("YANLIŞ: " +yanlisSayac);

        dogruSoru = sorularListe.get(soruSayac);

        //her soru yüklendiğinde yanlış seçenekleri almam gerekiyor:
        yanlisSeceneklerListe = new BayraklarDao().rastgele3YanlisSecenekGetir(vt, dogruSoru.getBayrak_id());

        //soeuyu getirdim, yanlış seçenekleri aldım, şimdi arayüzü değiştirmem lazım:
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(), "drawable", getPackageName()));

        //şimdi seçenekleri yerleştirmem gerekiyor:
        secenekleriKaristirmaListe.clear(); //her seferinde farklı seçenekler olacağı için temizlemem lazım.
        secenekleriKaristirmaListe.add(dogruSoru);
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(2));

        seceneklerListe.clear();
        for (Bayraklar b: secenekleriKaristirmaListe){
            seceneklerListe.add(b);
        }

        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());

    }

    //doğruluk kontrolunu sağlayacak metodu yazalım:
    //seceneğe tıkladığım anda doğru cevapla arayüzdeki yazı kıyaslanacak:

    public void dogruKontrol(Button button){
        //tıkladığım anda buttonyazıyı almam gerekiyor:
        String buttonYazi = button.getText().toString(); //tostring ile yazısını verecek:
        String dogruCevap = dogruSoru.getBayrak_ad();    //dogru sorunun bayrak adı kısmı benim doğru cevabımı verir.

        if (buttonYazi.equals(dogruCevap)){
            dogruSayac++;
        }else{
            yanlisSayac++;
        }

    }

    //şimdi sayaç kontrolunu yapıyorum:
    public void sayacKontrol(){
        soruSayac++;
        if(soruSayac!=5){
            soruYukle();
        }else{  //eger soruSayacım 5 olmuşsa test bitmiştir result activitye geçiş sağlanacak:
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("dogruSayac", dogruSayac);
            startActivity(intent);   //bu ekilde öbür tarafa doğru sayısını göndermiş oldum
            finish();
        }

    }
}











package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewSonuc, textViewYuzdeSonuc;
    private Button buttonTekrar;
    private int dogruSayac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc);
        buttonTekrar = findViewById(R.id.buttonTekrar);

        dogruSayac = getIntent().getIntExtra("dogruSayac", 0);
        //arayüze bunu yazdırmam lazım:
        textViewSonuc.setText(dogruSayac+ " DOĞRU"  +(5-dogruSayac)+ " YANLIŞ");
        textViewYuzdeSonuc.setText("% "+(dogruSayac*100)/5+ "BAŞARI");

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ResultActivity.this, QuizActivity.class ));
                finish();  //result sayfasını da backstackten silmek istiyorum.

            }
        });
    }
}
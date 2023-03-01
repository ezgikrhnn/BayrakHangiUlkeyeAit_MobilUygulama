package com.example.bayrakuygulamasi;

//bu sınıfı veri tabanı çalışması yaparken kullanmak için oluşturuyorum.
// vurası veri tabanı içindeki tablomuzu temsil ediyor.
public class Bayraklar {

    private int bayrak_id;
    private String bayrak_ad;
    private String bayrak_resim;


    public Bayraklar() {  //bos const. oluşturdum.
    }

    public Bayraklar(int bayrak_id, String bayrak_ad, String bayrak_resim) { //dolu const. oluşturuyorum.
        this.bayrak_id = bayrak_id;
        this.bayrak_ad = bayrak_ad;
        this.bayrak_resim = bayrak_resim;
    }

    //getter setterları oluşturdum:
    public int getBayrak_id() {
        return bayrak_id;
    }

    public void setBayrak_id(int bayrak_id) {
        this.bayrak_id = bayrak_id;
    }

    public String getBayrak_ad() {
        return bayrak_ad;
    }

    public void setBayrak_ad(String bayrak_ad) {
        this.bayrak_ad = bayrak_ad;
    }

    public String getBayrak_resim() {
        return bayrak_resim;
    }

    public void setBayrak_resim(String bayrak_resim) {
        this.bayrak_resim = bayrak_resim;
    }
}

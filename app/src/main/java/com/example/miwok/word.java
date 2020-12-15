package com.example.miwok;

public class word {
    private String english;
    private String miwok;
    private int resid = -1;
    private int audioid;


    public word(String menglish,String mmiwok,int maudioid){
        english = menglish;
        miwok = mmiwok;
        audioid = maudioid;
    }
    public word(String menglish,String mmiwok,int mresid,int maudioid){
        english = menglish;
        miwok = mmiwok;
        resid = mresid;
        audioid = maudioid;
    }
    public String getEnglish(){
        return english;
    }
    public String getMiwok(){
        return miwok;
    }
    public int getResid() { return resid; }
    public int getAudioid() {return audioid; }
    public boolean hasImage() {return resid != -1; }
}

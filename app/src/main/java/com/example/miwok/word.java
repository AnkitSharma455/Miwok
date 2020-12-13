package com.example.miwok;

public class word {
    private String english;
    private String miwok;
    private int resid = -1;


    public word(String menglish,String mmiwok){
        english = menglish;
        miwok = mmiwok;
    }
    public word(String menglish,String mmiwok,int mresid){
        english = menglish;
        miwok = mmiwok;
        resid = mresid;
    }
    public String getEnglish(){
        return english;
    }
    public String getMiwok(){
        return miwok;
    }
    public int getResid() { return resid; }
    public boolean hasImage() {return resid != -1; }
}

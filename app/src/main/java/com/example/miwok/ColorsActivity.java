package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<word> words = new ArrayList<>();
        words.add(new word("red", "weṭeṭṭi"));
        words.add(new word("mustard yellow", "chiwiiṭә"));
        words.add(new word("dusty yellow", "ṭopiisә"));
        words.add(new word("green", "chokokki"));
        words.add(new word("brown", "ṭakaakki"));
        words.add(new word("gray", "ṭopoppi"));
        words.add(new word("black", "kululli"));
        words.add(new word("white", "kelelli"));

        wordAdapter adapter = new wordAdapter(this, words);
        ListView listView =  findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
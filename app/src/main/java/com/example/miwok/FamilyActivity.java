package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<word> words = new ArrayList<>();
        words.add(new word("father", "әpә"));
        words.add(new word("mother", "әṭa"));
        words.add(new word("son", "angsi"));
        words.add(new word("daughter", "tune"));
        words.add(new word("older brother", "taachi"));
        words.add(new word("younger brother", "chalitti"));
        words.add(new word("older sister", "teṭe"));
        words.add(new word("younger sister", "kolliti"));
        words.add(new word("grandmother ", "ama"));
        words.add(new word("grandfather", "paapa"));

        wordAdapter adapter = new wordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
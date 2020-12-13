package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<word> words = new ArrayList<>();
        words.add(new word("one","lutti"));
        words.add(new word("two", "otiiko"));
        words.add(new word("three", "tolookosu"));
        words.add(new word("four", "oyyisa"));
        words.add(new word("five", "massokka"));
        words.add(new word("six", "temmokka"));
        words.add(new word("seven", "kenekaku"));
        words.add(new word("eight", "kawinta"));
        words.add(new word("nine", "wo’e"));
        words.add(new word("ten", "na’aacha"));

        wordAdapter adapter = new wordAdapter(this, words);
        ListView listView =  findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
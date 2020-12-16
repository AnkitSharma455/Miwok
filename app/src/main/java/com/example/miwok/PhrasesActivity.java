package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasemediaplayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<word> words = new ArrayList<>();
        words.add(new word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new word("Come here.", "әnni'nem",R.raw.phrase_come_here));

        wordAdapter adapter = new wordAdapter(this, words,R.color.category_phrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releasemediaplayer();
                word currentword = words.get(position);
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,currentword.getAudioid());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mcompletionListener);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void releasemediaplayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}


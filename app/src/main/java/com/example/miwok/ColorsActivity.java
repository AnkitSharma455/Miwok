package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    private AudioManager.OnAudioFocusChangeListener maudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releasemediaplayer();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mcompletionlistener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasemediaplayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> words = new ArrayList<>();
        words.add(new word("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new word("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("black", "kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new word("white", "kelelli",R.drawable.color_white,R.raw.color_white));

        wordAdapter adapter = new wordAdapter(this, words,R.color.category_colors);
        ListView listView =  findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word currentword = words.get(position);
                releasemediaplayer();
                int result = audioManager.requestAudioFocus(maudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this,currentword.getAudioid());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mcompletionlistener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasemediaplayer();
    }

    private void releasemediaplayer(){
        if (mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(maudioFocusChangeListener);
        }
    }
}
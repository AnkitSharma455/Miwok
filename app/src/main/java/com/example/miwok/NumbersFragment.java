package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener monAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersFragment newInstance(String param1, String param2) {
        NumbersFragment fragment = new NumbersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> words = new ArrayList<>();
        words.add(new word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new word("two", "otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new word("three", "tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new word("four", "oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new word("five", "massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new word("six", "temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new word("seven", "kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("eight", "kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new word("nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new word("ten", "na’aacha",R.drawable.number_ten,R.raw.number_ten));

        wordAdapter adapter = new wordAdapter(getActivity(), words,R.color.category_numbers);
        ListView listView =  rootview.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word currentword = words.get(position);
                releasemediaplayer();
                int result = audioManager.requestAudioFocus(monAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(getActivity(),currentword.getAudioid());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mcompletionlistener);
                }
            }
        });

        return rootview;
    }
    @Override
    public void onStop() {
        releasemediaplayer();
        super.onStop();
    }


    private void releasemediaplayer(){
        if (mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(monAudioFocusChangeListener);
        }
    }
}

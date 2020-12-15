package com.example.miwok;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<word> {

    private int colorid;
    public wordAdapter(@NonNull Context context, ArrayList<word> words,int mcolorid) {
        super(context,0, words);
        colorid = mcolorid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word currentWord = getItem(position);
        TextView miwokTextView = listItemView.findViewById(R.id.miwok);
        miwokTextView.setText(currentWord.getMiwok());

        TextView englishTextView = listItemView.findViewById(R.id.english);
        englishTextView.setText(currentWord.getEnglish());

        ImageView images = listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()){
            images.setImageResource(currentWord.getResid());
            images.setVisibility(View.VISIBLE);
        }
        else
            images.setVisibility(View.GONE);


        View textcontainer = listItemView.findViewById(R.id.textcontainer);
        int color = ContextCompat.getColor(getContext(),colorid);
        textcontainer.setBackgroundColor(color);

        return listItemView;
    }
}

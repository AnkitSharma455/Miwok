package com.example.miwok;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<word> {

    public wordAdapter(@NonNull Context context, ArrayList<word> words) {
        super(context,0, words);
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

        return listItemView;
    }
}

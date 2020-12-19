package com.example.miwok;


import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mcontext;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mcontext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new NumbersFragment();
        else if (position == 1)
            return new FamilyFragment();
        else if (position == 2)
            return new ColorsFragment();
        else
            return new PhrasesFragment();

    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return mcontext.getString(R.string.category_numbers);
        else if (position == 1)
            return mcontext.getString(R.string.category_family);
        else if (position == 2)
            return mcontext.getString(R.string.category_colors);
        else
            return mcontext.getString(R.string.category_phrases);
    }
}

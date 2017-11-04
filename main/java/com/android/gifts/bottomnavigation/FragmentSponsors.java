package com.android.gifts.bottomnavigation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nirav on 27-Dec-16.
 */

public class FragmentSponsors extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.sponsors_fragment, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.sponsors_fragment, container, false);
    }
}

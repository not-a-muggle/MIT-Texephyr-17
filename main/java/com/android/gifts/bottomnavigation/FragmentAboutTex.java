package com.android.gifts.bottomnavigation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import static com.android.gifts.bottomnavigation.R.id.container;

/**
 * Created by Nirav on 29-Dec-16.
 */

public class FragmentAboutTex extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.about_tex_fragment, container, false);
        return v;
    }
}

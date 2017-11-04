package com.android.gifts.bottomnavigation;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import static android.R.id.list;

/**
 * Created by Nirav on 27-Dec-16.
 */

public class FragmentMore extends ListFragment implements AdapterView.OnItemClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.more_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.moreTabs, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent myIntent;
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        if (position == 0) {
             myIntent = new Intent(view.getContext(), VolunteerLoginActivity.class);
            startActivityForResult(myIntent, 0);
        }
        if (position == 1) {
            Uri gmmIntentUri = Uri.parse("geo:18.5181,73.8150?q=Maharashtra Institute Of Technology, Pune");
            myIntent= new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            myIntent.setPackage("com.google.android.apps.maps");
            if (myIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(myIntent);
            }
        }
        if (position == 2) {
            String url = "http://www.texephyr.com";
            myIntent = new Intent(Intent.ACTION_VIEW);
            myIntent.setData(Uri.parse(url));
            startActivity(myIntent);
        }
        if (position == 3) {
            myIntent = new Intent(view.getContext(), DevelopmentTeam.class);
            startActivityForResult(myIntent, 0);
        }


    }


}

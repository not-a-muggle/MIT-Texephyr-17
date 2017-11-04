package com.android.gifts.bottomnavigation;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;
    private TypedArray mImageSet;
    private String[] mDescription;
    private String[] mRules;
    private String[] mContacts;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
    //    private final ImageView image;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog d = new Dialog(context);
                    d.setContentView(R.layout.dialog_event);
                    d.setTitle(mDataSet[getPosition()]);
                    d.setCancelable(true);

                    TextView text_desc = (TextView) d.findViewById(R.id.desc);
                    text_desc.setText(mDescription[getPosition()]);

                    TextView text_rules = (TextView) d.findViewById(R.id.rules);
                    text_rules.setText(mRules[getPosition()]);

                    TextView text_contact = (TextView) d.findViewById(R.id.contact);
                    text_contact.setText(mContacts[getPosition()]);

                    ImageView img = (ImageView) d.findViewById(R.id.image);
                    img.setImageDrawable(mImageSet.getDrawable(getPosition()));

                    d.show();
                }
            });
            name = (TextView) v.findViewById(R.id.name);
      //      image = (ImageView) v.findViewById(R.id.image);

        }

        public TextView getTextView() {
            return name;
        }
        //public ImageView getImageView() { return image; }

    }

    public CustomAdapter(String[] dataSet, TypedArray imageSet, String[] descrptions, String[] rules, String[] contacts, Context c) {
        mDataSet = dataSet;
        mImageSet = imageSet;
        mDescription = descrptions;
        mRules = rules;
        mContacts = contacts;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTextView().setText(mDataSet[position]);
       // viewHolder.getImageView().setImageDrawable(mImageSet.getDrawable(position));

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
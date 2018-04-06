package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {
    public MasterListFragment() {

    }

    //Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(
                R.layout.fragment_master_list,
                container,
                false);

        //Get a reference to the GridView in the Fragment master list layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        //Return the root view
        return rootView;
    }
}

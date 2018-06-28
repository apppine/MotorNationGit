package com.samsson.motornation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsson.motornation.R;
import com.samsson.motornation.adapter.Melist_adapter;
import com.samsson.motornation.model.Home;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    public MeFragment() {
        // Required empty public constructor
    }
    RecyclerView me_recycler;
    Melist_adapter melist_adapter;
    public ArrayList<Home> Homelist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_me, container, false);
        me_recycler=root.findViewById(R.id.me_recycler);
        me_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        LoadData();
        melist_adapter=new Melist_adapter(getContext(),Homelist);
        me_recycler.setAdapter(melist_adapter);
        return root;
    }
    public void LoadData()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        me_recycler.setAdapter(melist_adapter);
    }
}

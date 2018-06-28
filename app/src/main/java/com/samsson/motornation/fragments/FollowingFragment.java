package com.samsson.motornation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsson.motornation.R;
import com.samsson.motornation.adapter.Followinglist_adapter;
import com.samsson.motornation.model.Home;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {

    RecyclerView me_recycler;
    Followinglist_adapter followinglist_adapter;
    public ArrayList<Home> Homelist;
    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_following, container, false);
        me_recycler=root.findViewById(R.id.following_recycler);
        me_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        LoadData();
        followinglist_adapter=new Followinglist_adapter(getContext(),Homelist);
        me_recycler.setAdapter(followinglist_adapter);
        return root;
    }
    public void LoadData()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        me_recycler.setAdapter(followinglist_adapter);
    }
}

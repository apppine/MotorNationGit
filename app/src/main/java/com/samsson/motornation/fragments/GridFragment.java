package com.samsson.motornation.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsson.motornation.R;
import com.samsson.motornation.activity.PreviewActivity;
import com.samsson.motornation.adapter.Showcasedlist_adapter;
import com.samsson.motornation.model.Home;
import com.samsson.motornation.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    RecyclerView recyclerView;
    Showcasedlist_adapter showcasedlist_adapter;
    public ArrayList<Home> Homelist;
    public GridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_grid, container, false);
        recyclerView= (RecyclerView)root.findViewById(R.id.grid_recycler);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        LoadData();
        showcasedlist_adapter=new Showcasedlist_adapter(getContext(),Homelist);
        recyclerView.setAdapter(showcasedlist_adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(2), true));

        recyclerView.addOnItemTouchListener(new SearchFragment.RecyclerTouchListener(getContext(), recyclerView, new SearchFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PreviewActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        return root;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public void LoadData()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        recyclerView.setAdapter(showcasedlist_adapter);
    }
}

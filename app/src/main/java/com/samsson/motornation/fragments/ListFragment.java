package com.samsson.motornation.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsson.motornation.R;
import com.samsson.motornation.activity.PreviewActivity;
import com.samsson.motornation.adapter.Linearlist_adapter;
import com.samsson.motornation.model.Home;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    RecyclerView linear_recyc;
    public ArrayList<Home> Homelist;
    Linearlist_adapter linearlist_adapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_list, container, false);
        linear_recyc= (RecyclerView)root.findViewById(R.id.linear_recycler);

        linear_recyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        LoadData2();
        linearlist_adapter=new Linearlist_adapter(getContext(),Homelist);
        linear_recyc.setAdapter(linearlist_adapter);
        linear_recyc.addOnItemTouchListener(new SearchFragment.RecyclerTouchListener(getContext(), linear_recyc, new SearchFragment.ClickListener() {
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

    public void LoadData2()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());

        linear_recyc.setAdapter(linearlist_adapter);
    }
}

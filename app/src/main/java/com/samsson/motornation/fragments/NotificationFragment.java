package com.samsson.motornation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.samsson.motornation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    LinearLayout followingLinear,meLinear;
   // TextView tv_Following,tv_Me;
    View followingview,meview;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_notification, container, false);

        Fragment fragment = new FollowingFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.notificationtframe, fragment).commit();

        followingLinear=root.findViewById(R.id.following);
        meLinear=root.findViewById(R.id.me);
        followingview=root.findViewById(R.id.view1);
        meview=root.findViewById(R.id.view2);
        followingLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                followingview.setBackgroundColor(getResources().getColor(R.color.yellow_dark));
                meview.setBackgroundColor(getResources().getColor(R.color.white));


                Fragment fragment = new FollowingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.notificationtframe, fragment).commit();
            }
        });
        meLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followingview.setBackgroundColor(getResources().getColor(R.color.white));
                meview.setBackgroundColor(getResources().getColor(R.color.yellow_dark));


                Fragment fragment = new MeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.notificationtframe, fragment).commit();
            }
        });
        return root;
    }

}
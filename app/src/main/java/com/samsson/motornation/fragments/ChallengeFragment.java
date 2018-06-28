package com.samsson.motornation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.samsson.motornation.R;


public class ChallengeFragment extends Fragment {
    LinearLayout nearbyLinear,challengesLinear;
    // TextView tv_Following,tv_Me;
    View nearbyview,challengeview;
    public ChallengeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_challenge, container, false);
        Fragment fragment = new NearbyFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.challengeframe, fragment).commit();

        nearbyLinear=root.findViewById(R.id.nearby);
        challengesLinear=root.findViewById(R.id.challenges);
        nearbyview=root.findViewById(R.id.view1);
        challengeview=root.findViewById(R.id.view2);
        nearbyLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nearbyview.setBackgroundColor(getResources().getColor(R.color.yellow_dark));
                challengeview.setBackgroundColor(getResources().getColor(R.color.white));


                Fragment fragment = new NearbyFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.challengeframe, fragment).commit();
            }
        });
        challengesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyview.setBackgroundColor(getResources().getColor(R.color.white));
                challengeview.setBackgroundColor(getResources().getColor(R.color.yellow_dark));


                Fragment fragment = new ChallengesubFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.challengeframe, fragment).commit();
            }
        });




        return  root;
    }

}

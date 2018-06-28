package com.samsson.motornation.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.samsson.motornation.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class  NearbyFragment extends Fragment {

ImageView nearByFriends,gasStations,parkingSlots,workShops;
    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_nearby, container, false);
        nearByFriends=root.findViewById(R.id.nearby_friends);
        gasStations=root.findViewById(R.id.gas_station);
        parkingSlots=root.findViewById(R.id.parking_slots);
        workShops=root.findViewById(R.id.work_shops);

        nearByFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        gasStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?&q= Fuel Station";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        parkingSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?&q= Parking Slots";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        workShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?&q=Auto Repairing Workshops";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });

        return root;
    }

}

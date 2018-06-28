package com.samsson.motornation.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetworkConnection {
    public  boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Log.e("Network Testing", "***Available***");
            return true;
        }
      //  Log.e("Network Testing", "***Not Available***");
        return false;

    }
}
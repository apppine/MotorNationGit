package com.samsson.motornation.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constantvariables {

    /* Controllers*/
    public static String BASE_URL="";
    public static String SIGNIN_CONTROLLER="";
    public static String SIGNUP_CONTROLLER="";

    /* Strings */
    public static String choose="choose";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static  String IS_INSTALL = "IsInstall";
    public static  String LANG = "Lang";

    public static   int isInstall=0;
    public static   String Lang="";

    public static void setCustomerId(Context context, String user_id) {
        editor = context.getSharedPreferences(choose, Context.MODE_PRIVATE).edit();
        editor.putString("user_type", user_id);
        editor.commit();
    }
    public static String getCustomerId(Context context) {
        sharedPreferences = context.getSharedPreferences(choose, Context.MODE_PRIVATE);
        String UserId = sharedPreferences.getString("user_type", "");
        return UserId;
    }

    public static void setIsInstall(Context context, int install) {
        editor = context.getSharedPreferences(IS_INSTALL, Context.MODE_PRIVATE).edit();
        editor.putInt("install", install);
        editor.commit();
    }

    public static int getIsInstall(Context context) {
        sharedPreferences = context.getSharedPreferences(IS_INSTALL, Context.MODE_PRIVATE);
        isInstall = sharedPreferences.getInt("install", 0);
        return isInstall;
    }

    public static void setIdentityProvider(Context context, String lang) {
        editor = context.getSharedPreferences(LANG, Context.MODE_PRIVATE).edit();
        editor.putString("IdentityProvider", lang);
        editor.commit();
    }

    public static String getIdentityProvider(Context context) {
        sharedPreferences = context.getSharedPreferences(LANG, Context.MODE_PRIVATE);
        Lang = sharedPreferences.getString("IdentityProvider", "");
        return Lang;
    }
}

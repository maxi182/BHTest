package com.babyloncodetest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class Utils {

    public static final String AVATAR_BASE_URL = "https://api.adorable.io/avatars/120/";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
    }

}

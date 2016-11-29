package com.wildwolf.mycsdn.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class SnackbarUtil {
    public static void show(View rootView, int textId) {
        Snackbar.make(rootView, textId, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View rootView, String text) {
        Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT).show();
    }
}

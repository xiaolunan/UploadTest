package com.example.testapp.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class dpToPxUtils {
    public static float dpToPx(int dp) {
        return TypedValue.applyDimension(1, (float)dp, Resources.getSystem().getDisplayMetrics());
    }
}

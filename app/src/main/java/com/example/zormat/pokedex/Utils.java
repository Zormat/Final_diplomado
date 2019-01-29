package com.example.zormat.pokedex;

import android.app.Activity;
import android.content.Intent;

class Utils {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_WHITE = 1;

    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.night);
                break;
            case THEME_WHITE:
                activity.setTheme(R.style.AppTheme);
                break;

        }
    }
}

package xyz.jhughes.epsteinandroid.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper for shared preference operations. Allows the default shared prefs page to be
 * obtained consistently.
 */
public class SharedPrefsHelper {
    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("xyz.jhughes.epsteinandroid", Context.MODE_PRIVATE);
    }
}

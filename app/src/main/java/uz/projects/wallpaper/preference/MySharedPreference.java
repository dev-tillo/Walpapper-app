package uz.projects.wallpaper.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static final MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("myFileAll", Context.MODE_PRIVATE);
        }
        return mySharedPreference;
    }

    public String getNumberListString() {
        return sharedPreferences.getString("List", "");
    }

    public void setUserList(String list) {
        editor = sharedPreferences.edit();
        editor.putString("List", list);
        editor.apply();
    }

    public void clearCache() {
        editor.clear();
    }

}

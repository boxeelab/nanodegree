package com.nanodegreedjm.p1.app.popularmovies;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by dhruba on 2/5/16.
 */
public class MovieSettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getFragmentManager().beginTransaction().replace(android.R.id.content,new MovieSettingsFragment()).commit();
        super.onCreate(savedInstanceState);
    }
}

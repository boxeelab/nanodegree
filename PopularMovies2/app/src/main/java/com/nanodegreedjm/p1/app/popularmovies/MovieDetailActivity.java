package com.nanodegreedjm.p1.app.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dhruba on 2/5/16.
 */
public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = new MovieDetailFragment();
            fragment.setArguments(getIntent().getBundleExtra(MovieInfo.MOVIE_INFO_PARCELABLE));
            fragmentManager.beginTransaction().add(R.id.movie_detail_container, fragment).commit();
        }
    }
}

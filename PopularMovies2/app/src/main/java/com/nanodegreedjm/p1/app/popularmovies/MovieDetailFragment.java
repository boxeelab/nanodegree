package com.nanodegreedjm.p1.app.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dhruba on 2/5/16.
 */
public class MovieDetailFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);


        // get content here
        Bundle bundle = getArguments();
        if (bundle != null) {
            final MovieInfo movieInfo = bundle.getParcelable(MovieInfo.MOVIE_INFO_PARCELABLE);
            this.getActivity().setTitle(movieInfo.getMovieTitle());
            final MovieInfoViewHelper movieDetailView = new MovieInfoViewHelper(view);
            if (movieInfo != null) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        movieDetailView.set(movieInfo, getContext());
                    }
                });

            }
        }
        return view;
    }
}

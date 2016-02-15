package com.nanodegreedjm.p1.app.popularmovies;

import android.net.Uri;

/**
 * Created by dhruba on 2/9/16.
 */
public class TheMovieDBContract {
    //public static

    public static final String POPULARITY_DESC = "popularity.desc";
    public static final String VOTE_AVERAGE_DESC = "vote_average.desc";

    final static String API_KEY = "api_key";
    final static String SORT_BY = "sort_by";
    final static String BASE_URL = "http://api.themoviedb.org/3/discover/movie";
    final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";

    final static String IMAGE_SIZE_W92 = "w92";
    final static String IMAGE_SIZE_W154 = "w154";
    final static String IMAGE_SIZE_W185 = "w185";
    final static String IMAGE_SIZE_W342 = "w342";
    final static String IMAGE_SIZE_W500 = "w500";
    final static String IMAGE_SIZE_W780 = "w780";
    final static String IMAGE_SIZE_ORIGINAL =   "original";


    public static Uri getMovieDiscoverUri(String sortOrder)
    {
        return Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY, BuildConfig.OPEN_MOVIE_API_KEY)
                .appendQueryParameter(SORT_BY, sortOrder).build();
    }

    public static Uri getImageUri(String imagePath,String size) {
        return Uri.withAppendedPath(Uri.parse(TheMovieDBContract.IMAGE_BASE_URL).buildUpon()
                .appendPath(size).build(),imagePath);

    }

}

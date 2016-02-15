package com.nanodegreedjm.p1.app.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by dhruba on 2/9/16.
 */
public class MovieInfoViewHelper {

    private TextView mTextViewTitle;
    private TextView mTextViewOverview;
    private TextView mTextReleaseDate;
    private TextView mTextViewVote;
    private ImageView mImageViewThumbNail;
    private RatingBar mRatingBar;

    public MovieInfoViewHelper(View view)
    {
        mTextViewOverview = (TextView)view.findViewById(R.id.text_movie_overview);
        mTextReleaseDate = (TextView)view.findViewById(R.id.text_movie_release_date);
        mTextViewTitle = (TextView)view.findViewById(R.id.text_title);
        mTextViewVote = (TextView)view.findViewById(R.id.text_movie_rating);
        mImageViewThumbNail = (ImageView)view.findViewById(R.id.image_view_thumbnail);
        //mRatingBar = (RatingBar)view.findViewById(R.id.ratingBar2);
    }

    public void set(MovieInfo movie,Context context)
    {

        mTextReleaseDate.setText(Utility.getDayString(movie.getReleaseDate()));
        float voteAverage = movie.getVoteAverage();
        mTextViewVote.setText(Utility.getFormatRating(voteAverage));
        mTextViewTitle.setText(movie.getMovieTitle());
        mTextViewOverview.setText(movie.getMovieOverView());
        int width = mImageViewThumbNail.getWidth();
        int height = (int)(width *.6f);
        Uri bannerImage = TheMovieDBContract.getImageUri(movie.getPosterImagePath(),TheMovieDBContract.IMAGE_SIZE_W342);
        Picasso.with(context).load(bannerImage.toString())
                .resize(width, height)
                .centerCrop().into(mImageViewThumbNail);

    }
}

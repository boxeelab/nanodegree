package com.nanodegreedjm.p1.app.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhruba on 2/5/16.
 */
public class MovieInfo implements Parcelable {

    public static String MOVIE_INFO_PARCELABLE =  MovieInfo.class.getName();
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /* json object attribute name */
    final String ID = "id";
    final String TITLE = "original_title";
    final String BACKDROP_PATH = "backdrop_path";
    final String POSTER_PATH = "poster_path";
    final String RELEASE_DATE = "release_date";
    final String OVERVIEW = "overview";
    final String VOTE_AVERAGE = "vote_average";
    final static String LOG_TAG = MovieInfo.class.getName();

    private long mMovieId;
    private String mImageThumbnailPath;
    private String mPosterImagePath;
    private long mReleaseDate;
    private String mMovieTitle;
    private String mMovieOverView;
    private float mVoteAverage;


    public long getMovieId() {
        return mMovieId;
    }

    public String getImageThumbnailPath() {
        return mImageThumbnailPath;
    }

    public String getPosterImagePath() {
        return mPosterImagePath;
    }

    public long getReleaseDate() {
        return mReleaseDate;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public String getMovieOverView() {
        return mMovieOverView;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }




    protected MovieInfo(Parcel in) {

        mMovieId = in.readLong();
        mImageThumbnailPath = in.readString();
        mPosterImagePath = in.readString();
        mReleaseDate = in.readLong();
        mMovieTitle = in.readString();
        mMovieOverView = in.readString();
        mVoteAverage = in.readFloat();
    }

    protected MovieInfo() {
    }

    public void update(JSONObject jsonObject) {
        try {
            mMovieId = Long.parseLong(jsonObject.getString(ID));
            mImageThumbnailPath = jsonObject.getString(POSTER_PATH);
            mPosterImagePath = jsonObject.getString(BACKDROP_PATH);
            mReleaseDate= Utility.getLong(jsonObject.getString(RELEASE_DATE),MovieInfo.DATE_FORMAT);
            mMovieTitle = jsonObject.getString(TITLE);
            mMovieOverView = jsonObject.getString(OVERVIEW);
            mVoteAverage = Float.parseFloat(jsonObject.getString(VOTE_AVERAGE));
        } catch (JSONException e) {
            Log.e(LOG_TAG,"Error parsing JSON object:" + jsonObject.toString());
        }
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mMovieId);
        dest.writeString(mImageThumbnailPath);
        dest.writeString(mPosterImagePath);
        dest.writeLong(mReleaseDate);
        dest.writeString(mMovieTitle);
        dest.writeString(mMovieOverView);
        dest.writeFloat(mVoteAverage);
    }
}

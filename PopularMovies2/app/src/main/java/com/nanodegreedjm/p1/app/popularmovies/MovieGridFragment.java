package com.nanodegreedjm.p1.app.popularmovies;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dhruba on 2/5/16.
 * MovieGridFragment
 */
public class MovieGridFragment extends Fragment implements AdapterView.OnItemClickListener {


    private boolean isRunning = false;
    private MovieFetchTask mMovieFetchTask;
    private MovieGridAdapter mGridAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO: I am not sure i have to implement this for this fragment
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        mGridAdapter = new MovieGridAdapter(this.getActivity(), new ArrayList<MovieInfo>());
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movie);
        gridView.setAdapter(mGridAdapter);
        gridView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isRunning) {
            mMovieFetchTask = new MovieFetchTask();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
            String sortOrder = sharedPreferences.getString("sort_order", TheMovieDBContract.POPULARITY_DESC);
            mMovieFetchTask.execute(sortOrder);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isRunning) {
            isRunning = false;
            mMovieFetchTask.cancel(true);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    protected void updateAdapter(List<MovieInfo> movieInfos) {

        if (mGridAdapter != null) {
            mGridAdapter.clear();
            for (MovieInfo movieInfo : movieInfos) {
                mGridAdapter.add(movieInfo);
            }
            mGridAdapter.notifyDataSetChanged();
        }
        isRunning = false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieInfo movieInfo = (MovieInfo) view.getTag();
        Intent intent = new Intent(this.getActivity(), MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(MovieInfo.MOVIE_INFO_PARCELABLE, movieInfo);
        intent.putExtra(MovieInfo.MOVIE_INFO_PARCELABLE, bundle);
        startActivity(intent);
    }



    class MovieFetchTask extends AsyncTask<String, Integer, List<MovieInfo>> {

        final String LOG_TAG = MovieFetchTask.class.getName();


        @Override
        protected List<MovieInfo> doInBackground(String... params) {
            String response;
            Uri uri = TheMovieDBContract.getMovieDiscoverUri(params[0]);
            try {
                URL url = new URL(uri.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder jsonResponse = new StringBuilder();
                while ((response = bufferedReader.readLine()) != null) {
                    jsonResponse.append(response);
                }
                JSONObject jsonMovieInfos = new JSONObject(jsonResponse.toString());
                return buildMovieList(jsonMovieInfos);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MovieInfo> movieInfos) {
            super.onPostExecute(movieInfos);
            MovieGridFragment.this.updateAdapter(movieInfos);
        }

        private List<MovieInfo> buildMovieList(JSONObject jsonMovieInfos) {
            final String RESULTS = "results";
            List<MovieInfo> list = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonMovieInfos.getJSONArray(RESULTS);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonMovieInfo = jsonArray.getJSONObject(i);
                        MovieInfo movieInfo = new MovieInfo();
                        movieInfo.update(jsonMovieInfo);
                        list.add(movieInfo);
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "JSON parsing failed: JSON array to JSON");
                    }
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            MovieGridFragment.this.isRunning = false;
        }
    }


}

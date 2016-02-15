package com.nanodegreedjm.p1.app.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dhruba on 2/5/16.
 */
public class MovieGridAdapter extends ArrayAdapter<MovieInfo> {


    public MovieGridAdapter(Activity context, List<MovieInfo> movieList) {
        super(context, 0, movieList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieInfo movieInfo = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_grid_movie_view, parent, false);
        }
        final Uri thumbnailUri
                = TheMovieDBContract.getImageUri(movieInfo.getImageThumbnailPath(),
                TheMovieDBContract.IMAGE_SIZE_W185);

        final Context context = convertView.getContext();
        convertView.setTag(movieInfo);
        final ImageView iconView = (ImageView) convertView.findViewById(R.id.image_view_thumbnail);
        iconView.post(new Runnable() {
            @Override
            public void run() {
                int width = iconView.getWidth();
                if (width > 0) {
                    Picasso.with(context).load(thumbnailUri.toString())
                            .resize(width, (int) (width * 1.2f))
                            .centerCrop().into(iconView);
                }
            }
        });
        return convertView;
    }


}

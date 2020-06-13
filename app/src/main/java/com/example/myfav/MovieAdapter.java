package com.example.myfav;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    Context context;
    int resource;

    public MovieAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView movieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
        TextView movieDirector = (TextView) convertView.findViewById(R.id.movieDirector);
        TextView movieRate = (TextView) convertView.findViewById(R.id.movieRate);
        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.moviePoster);

        Movie currentMovie = getItem(position);

        movieTitle.setText(currentMovie.getTitle());
        movieDirector.setText(currentMovie.getDirector());
        //movieRate.setText(String.valueOf(currentMovie.getRate()));

        if ( currentMovie.getRate() == 0){
            movieRate.setText("");
        }else {
            movieRate.setText(String.valueOf(currentMovie.getRate()));
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentMovie.getPoster(),0,currentMovie.getPoster().length);
        moviePoster.setImageBitmap(bitmap);

        return convertView;
    }
}

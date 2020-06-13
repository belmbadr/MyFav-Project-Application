package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class myMovies extends AppCompatActivity {

    ListView moviesList;
    Button btnAdd;

    DatabaseMovies db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies);
        getSupportActionBar().hide();

        db = new DatabaseMovies(this);

        moviesList = (ListView) findViewById(R.id.moviesList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(myMovies.this,AddMovie.class);
                startActivity(intent);

            }
        });

        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie selected_movie = (Movie) parent.getItemAtPosition(position);

                Intent intent = new Intent(myMovies.this, UpdateMovie.class);

                intent.putExtra("id", selected_movie.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Movie> movies = db.getAllMovies();

        MovieAdapter movieAdapter = new MovieAdapter(this, R.layout.display,movies);

        moviesList.setAdapter(movieAdapter);
    }
}

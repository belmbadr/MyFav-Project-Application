package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button trendsBooks, myBooks, trendsMovies, myMovies, trendsSongs, mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        trendsBooks = (Button) findViewById(R.id.trendsBooks);
        myBooks = (Button) findViewById(R.id.myBooks);
        trendsMovies = (Button) findViewById(R.id.trendsMovies);
        myMovies = (Button) findViewById(R.id.myMovies);
        trendsSongs = (Button) findViewById(R.id.trendsSongs);
        mySongs = (Button) findViewById(R.id.mySongs);

        trendsBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,wtrendsBooks.class);
                startActivity(p1);
            }
        });

        myBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,myBooks.class);
                startActivity(p1);
            }
        });

        trendsMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,wtrendsMovies.class);
                startActivity(p1);
            }
        });

        myMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,myMovies.class);
                startActivity(p1);
            }
        });

        trendsSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,wtrendsSongs.class);
                startActivity(p1);
            }
        });

        mySongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(MainActivity.this,ListMusicActivity.class);
                startActivity(p1);
            }
        });
    }
}

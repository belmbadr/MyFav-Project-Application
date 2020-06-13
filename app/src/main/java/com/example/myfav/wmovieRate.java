package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class wmovieRate extends AppCompatActivity {

    WebView wv;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmovie_rate);
        getSupportActionBar().hide();

        wv = (WebView) findViewById(R.id.wv);
        btnBack = (Button) findViewById(R.id.btnBack);

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://help.imdb.com/article/imdb/track-movies-tv/ratings-faq/G67Y87TFYYP6TWAV#ratings");

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(wmovieRate.this,myMovies.class);
                startActivity(p);
            }
        });
    }
}
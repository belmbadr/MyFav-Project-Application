package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class wtrendsMovies extends AppCompatActivity {

    WebView wv;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtrends_movies);
        getSupportActionBar().hide();

        wv = (WebView) findViewById(R.id.wv);
        btnBack = (Button) findViewById(R.id.btnBack);

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://www.imdb.com/chart/moviemeter/");

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(wtrendsMovies.this,MainActivity.class);
                startActivity(p);
            }
        });
    }
}

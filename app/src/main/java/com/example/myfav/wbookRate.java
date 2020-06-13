package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class wbookRate extends AppCompatActivity {

    WebView wv;
    Button btnBack;
    //String uriString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbook_rate);
        getSupportActionBar().hide();

        wv = (WebView) findViewById(R.id.wv);
        btnBack = (Button) findViewById(R.id.btnBack);

        //Intent i = getIntent();
        //uriString = i.getStringExtra("text_label");

        wv.setWebViewClient(new WebViewClient());
        //wv.loadUrl("https://www.google.com/search?q=" + uriString + " goodreads rating"); //

        wv.loadUrl("https://www.goodreads.com/list/show/10198.Books_With_a_Goodreads_Average_Rating_of_4_5_or_higher_and_With_At_Least_100_Ratings");

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(wbookRate.this,myBooks.class);
                startActivity(p);
            }
        });
    }
}
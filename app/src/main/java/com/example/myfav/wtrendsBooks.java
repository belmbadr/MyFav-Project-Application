package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class wtrendsBooks extends AppCompatActivity {

    WebView wv;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtrends_books);
        getSupportActionBar().hide();

        wv = (WebView) findViewById(R.id.wv);
        btnBack = (Button) findViewById(R.id.btnBack);

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://www.amazon.com/best-sellers-books-Amazon/zgbs/books");

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(wtrendsBooks.this,MainActivity.class);
                startActivity(p);
            }
        });
    }
}

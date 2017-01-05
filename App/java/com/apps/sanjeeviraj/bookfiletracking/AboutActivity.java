package com.apps.sanjeeviraj.bookfiletracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.view.Menu.*;
import android.view.Window.*;
import android.webkit.WebViewClient;


public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        WebView about = (WebView) findViewById(R.id.webView_about);

        //about.loadUrl("http://10.0.2.2/book_tracking/rack_add.html");
        about.loadUrl("file:///android_asset/About.html");
    }

}

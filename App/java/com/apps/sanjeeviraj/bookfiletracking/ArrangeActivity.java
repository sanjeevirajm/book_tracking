package com.apps.sanjeeviraj.bookfiletracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ArrangeActivity extends AppCompatActivity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public static Integer flag_bar_qr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void scanBar(View v) {
        try {
            flag_bar_qr = 1;
            openArrangeRackDetails();
        } catch (Exception e) {
            Log.v("myapp", "Exception : " +e);
        }
    }

    public void scanQR(View v) {
        try {
            flag_bar_qr = 2;
            openArrangeRackDetails();
        } catch (Exception e) {
            Log.v("myapp", "Exception : " + e);
        }
    }

    public void openArrangeRackDetails()
    {
        Intent arrange_intent = new Intent(this, ArrangeRackDetails.class);
        startActivity(arrange_intent);
    }



}

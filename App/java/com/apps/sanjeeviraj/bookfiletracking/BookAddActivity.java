package com.apps.sanjeeviraj.bookfiletracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.util.Log;

public class BookAddActivity extends AppCompatActivity {
    public static String contents;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public Integer flag_bar_qr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void scanBar(View v) {
        try {
            flag_bar_qr = 1;
            Toast toast1 = Toast.makeText(this, "Barcode selected", Toast.LENGTH_LONG);
            toast1.show();
            Intent intent = new Intent(ACTION_SCAN);
           // intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(BookAddActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void scanQR(View v) {
        try {
            flag_bar_qr = 2;
            Log.v("MyApp", "QRcode selected");
            Toast toast1 = Toast.makeText(this, "QR code selected", Toast.LENGTH_LONG);
            toast1.show();

            Intent intent = new Intent(ACTION_SCAN);
            //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(BookAddActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Log.v("myapp", "BookAddActivity Line: 1");

                Toast toast2 = Toast.makeText(this, "Format: " + format + "Contents: " + contents, Toast.LENGTH_LONG);
                Log.v("myapp", "BookAddActivity Line: 2");
                toast2.show();
                Log.v("myapp", "BookAddActivity Line: 3");
                /*Intent settings_intent = new Intent(this, AddBookDetailsActivity.class);
                Log.v("myapp", " inside storeRackDetails() 7");
                startActivity(settings_intent);
                */
                Intent details_intent = new Intent(this, AddBookDetailsActivity.class);
                Log.v("myapp", "BookAddActivity Line: 4");
                details_intent.putExtra("flag_bar_qr", flag_bar_qr);
                Log.v("myapp", "BookAddActivity Line: 5");
                startActivity(details_intent);
                Log.v("myapp", "BookAddActivity Line: 6");
            }
        }
    }



}


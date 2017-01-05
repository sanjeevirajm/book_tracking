package com.apps.sanjeeviraj.bookfiletracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ArrangeScanActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;
    public static Integer flag_bar_qr = 0;
    public static Integer order_no;
    public Integer rack_no, shelf_no, total_no_of_books, cur_order_no;
    public static Integer located_book_id = 0;
    public static Integer book_id =0;
    public Integer scanned_book_id = 0;
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_arrange_scan);

            total_no_of_books = 0;
            sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);
            if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
                Log.v("myapp", " ModifyBookdetailsActivity Line: 1");
                IPValue = sharedpreferences.getString(IPAddress, "");
                UserNameValue = sharedpreferences.getString(UserName, "");
                PasswordValue = sharedpreferences.getString(Password, "");
                Log.v("myapp", "ArrangeScanActivity Line: 2");
                startProgram();
            }
            else
            {
                Log.v("myapp", "ArrangeScanActivity Line: 7");
                Toast.makeText(getApplicationContext(), "Enter IPAddress, Username, Password", Toast.LENGTH_LONG).show();
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                Log.v("myapp", "ArrangeScanActivity Line: 8");
                startActivity(settings_intent);
                Log.v("myapp", "ArrangeScanActivity Line: 9");
            }

        }
        catch (Exception e)
        {
            Log.v("myapp","Exception : "+e);
        }
    }

    public void startProgram() {
        rack_no = shelf_no = order_no = 0;
        while(flag_bar_qr == 0)
            flag_bar_qr = ArrangeActivity.flag_bar_qr;
        while(rack_no == 0)
            rack_no = ArrangeRackDetails.rack_no;
        while(shelf_no == 0)
            shelf_no = ArrangeRackDetails.shelf_no;
        while(order_no == 0)
            order_no = ArrangeRackDetails.order_no;
        Log.v("myapp1", "ArrangeScanActivity books Line: start Program: " + order_no);

        getBookDetails();
        getTotalNoOfBooks();

        if(flag_bar_qr == 1)
            scanBar();
        else if(flag_bar_qr == 2)
            scanQR();
    }

    public void getTotalNoOfBooks() {
        new PHPGetNoOfBooks(getApplicationContext()).execute(IPValue, String.valueOf(rack_no));
        while (PHPGetNoOfBooks.flag_book_no == 0) {
            total_no_of_books = PHPGetNoOfBooks.book_no;
            Log.v("myapp", "ArrangeScanActivity books Line: 4 : " + total_no_of_books);
        }
        PHPGetNoOfBooks.flag_book_no = 0;
    }

    public void getBookDetails() {
        new PHPGetBookID(getApplicationContext()).execute(IPValue, String.valueOf(flag_bar_qr), String.valueOf(rack_no), String.valueOf(shelf_no), String.valueOf(order_no));
        while (located_book_id == 0)
            located_book_id = PHPGetBookID.get_book_id;
        Log.v("myapp", "ArrangeScanActivity books Line: 4 : " + located_book_id);

    }

    public void scanQR() {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            //intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        }
        catch (ActivityNotFoundException anfe)
        {
            showDialog(ArrangeScanActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void scanBar() {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            //intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        }
        catch (ActivityNotFoundException anfe)
        {
            showDialog(ArrangeScanActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
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
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                contents = contents.trim();
                scanned_book_id = Integer.parseInt(contents);

                if(scanned_book_id == located_book_id)
                {
                    Log.v("myapp1", "ArrangeScanActivity books Line: if : " + located_book_id + "  " +scanned_book_id);
                    located_book_id = 0;
                    Toast toast = Toast.makeText(this, "Proceed", Toast.LENGTH_SHORT);
                    toast.show();
                    proceed();
                }
                else
                {
                    Log.v("myapp1", "ArrangeScanActivity books Line: else : " + located_book_id);
                    Toast toast = Toast.makeText(this, "Later", Toast.LENGTH_SHORT);
                    toast.show();
                }
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void proceed() {
        Log.v("myapp1", "ArrangeScanActivity books Line:  or 1 : " + order_no);
        order_no = order_no + 1;
        Log.v("myapp1", "ArrangeScanActivity books Line:  or 2 : " + order_no);
        if(order_no > total_no_of_books ) {
            Log.v("myapp1", "ArrangeScanActivity books Line: else : " + total_no_of_books);
            goBack();
        }
            else {
            PHPGetBookID.get_book_id = 0;
            located_book_id = 0;
            Log.v("myapp1", "ArrangeScanActivity books Line: else 1: " + located_book_id);
            //Log.v("myapp1", "ArrangeScanActivity books Line: else : " + located_book_id);
            //getBookDetails();
            new PHPGetBookID(getApplicationContext()).execute(IPValue, String.valueOf(flag_bar_qr), String.valueOf(rack_no), String.valueOf(shelf_no), String.valueOf(order_no));
            while (located_book_id == 0)
                located_book_id = PHPGetBookID.get_book_id;

            Log.v("myapp1", "ArrangeScanActivity books Line: else 2: " + located_book_id);
            if (flag_bar_qr == 1)
                scanBar();

            else if (flag_bar_qr == 2)
                scanQR();
        }
    }

    public void  showDetails1(View v)
    {
        book_id = scanned_book_id;
        Intent show_details1_intent = new Intent(this, ArrangeShowBookDetailsActivity.class);
        startActivity(show_details1_intent);
    }

    public void  showDetails2(View v)
    {
        book_id = located_book_id;
        Intent show_details2_intent = new Intent(this, ArrangeShowBookDetailsActivity.class);
        startActivity(show_details2_intent);
    }

    public void  skip(View v)
    {
        order_no = order_no + 1;
        proceed();
    }
    public void goBack() {
        Intent cancel_intent = new Intent(this, ArrangeActivity.class);
        Toast toast = Toast.makeText(this, "Scanning over", Toast.LENGTH_LONG);
        toast.show();
        startActivity(cancel_intent);
    }
}

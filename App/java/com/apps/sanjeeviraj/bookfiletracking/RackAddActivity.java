package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RackAddActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;

    EditText edit_nrack, edit_nshelf, edit_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rack_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.v("myapp", " inside oncreate 1");
        edit_nrack = (EditText) findViewById(R.id.edittext_noofrack_rackadd);
        Log.v("myapp", " inside oncreate 2");
        edit_nshelf = (EditText) findViewById(R.id.edittext_noofshelf_rackadd);
        edit_book = (EditText) findViewById(R.id.edittext_noofbook_rackadd);
        Log.v("myapp", " inside oncreate 3");

    }

    public void storeRackDetails(View v) {
        Log.v("myapp", " inside storeRackDetails() 1");
        String nrack  = edit_nrack.getText().toString();
        Log.v("myapp", " inside storeRackDetails() 2");
        String nshelf  = edit_nshelf.getText().toString();
        String nbook  = edit_book.getText().toString();
        Log.v("myapp", " inside storeRackDetails() 3");

        sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);
        Log.v("myapp", " inside storeRackDetails() 4");
        if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
            Log.v("myapp", " inside storeRackDetails() 5");
            IPValue = sharedpreferences.getString(IPAddress, "");
            UserNameValue = sharedpreferences.getString(UserName, "");
            PasswordValue = sharedpreferences.getString(Password, "");
            Log.v("myapp", " Before calling PHPRackAddctivity ");
            new PHPRackAddActivity(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue, nrack, nshelf, nbook);
            Log.v("myapp", " After calling PHPRackAddtActivity ");
        }
        else
        {
            Log.v("myapp", " inside storeRackDetails() 6");
            Toast.makeText(getApplicationContext(), "Enter IPAddress, Username, Password", Toast.LENGTH_LONG).show();
            Intent settings_intent = new Intent(this, SettingsActivity.class);
            Log.v("myapp", " inside storeRackDetails() 7");
            startActivity(settings_intent);
            Log.v("myapp", " inside storeRackDetails() 8");
        }
    }

    public void cancel(View v) {
        Intent cancel_intent = new Intent(this, MainActivity.class);
        startActivity(cancel_intent);
    }

}

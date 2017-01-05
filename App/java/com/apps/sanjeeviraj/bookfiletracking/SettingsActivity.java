package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.SharedPreferences;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText edit_ip, edit_uname, edit_pwd;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);

        edit_ip = (EditText) findViewById(R.id.edittext_ip_settings);
        edit_uname = (EditText) findViewById(R.id.edittext_username_settings);
        edit_pwd = (EditText) findViewById(R.id.edittext_password_settings);

        if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
            IPValue = sharedpreferences.getString(IPAddress, "");
            UserNameValue = sharedpreferences.getString(UserName, "");
            PasswordValue = sharedpreferences.getString(Password, "");
            edit_ip.setText(IPValue);
            edit_uname.setText(UserNameValue);
            edit_pwd.setText(PasswordValue);
        }
    }

    public void insertSettings(View v) {
        String ip  = edit_ip.getText().toString();
        String uname  = edit_uname.getText().toString();
        String pwd  = edit_pwd.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(IPAddress, ip);
        editor.putString(UserName, uname);
        editor.putString(Password, pwd);
        editor.commit();

        Toast.makeText(SettingsActivity.this, "Settings changed", Toast.LENGTH_LONG).show();
        Log.w(ip, "Settings changed");

        Intent main_intent = new Intent(this, MainActivity.class);
        startActivity(main_intent);

    }

    public void cancelSettings(View v) {
        Intent cancel_intent = new Intent(this, MainActivity.class);
        startActivity(cancel_intent);
    }

}

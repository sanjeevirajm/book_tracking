package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;
    public static ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        connect_to_mysql();
    }

    public void connect_to_mysql() {
        try {
            sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);
            if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
                IPValue = sharedpreferences.getString(IPAddress, "");
                UserNameValue = sharedpreferences.getString(UserName, "");
                PasswordValue = sharedpreferences.getString(Password, "");
                Log.v("myapp", " Before calling PHPConnectActivity ");
                new PHPConnectActivity(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
                Log.v("myapp", " After calling PHPConnectActivity ");
            } else {
                spinner.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Enter IPAddress, Username, Password", Toast.LENGTH_SHORT).show();
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
            }
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, "Error: "+e, Toast.LENGTH_SHORT).show();
            Log.v("myapp","Error: "+e);
        }
    }

    public void BookAdd(View v) {
        spinner.setVisibility(View.INVISIBLE);
        new PHPCheckBookAddAccess(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
        //Intent book_add_intent = new Intent(this, BookAddActivity.class);
        //startActivity(book_add_intent);
    }

    public void RackAdd(View v) {
        spinner.setVisibility(View.INVISIBLE);
        new PHPCheckRackAddAccess(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
        //Intent rackadd_intent = new Intent(this, RackAddActivity.class);
        //startActivity(rackadd_intent);
    }

    public void Modify(View v) {
        spinner.setVisibility(View.INVISIBLE);
        new PHPCheckModifyAccess(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
        //Intent modify_intent = new Intent(this, ModifyActivity.class);
        //startActivity(modify_intent);
    }

    public void Search(View v) {
        spinner.setVisibility(View.INVISIBLE);
        new PHPCheckSearchAccess(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
        //Intent search_intent = new Intent(this, SearchActivity.class);
        //startActivity(search_intent);
    }

    public void Arrange(View v) {
     try {


        spinner.setVisibility(View.INVISIBLE);
        new PHPCheckArrangeAccess(getApplicationContext()).execute(IPValue, UserNameValue, PasswordValue);
        //Intent arrange_intent = new Intent(this, ArrangeRackDetails.class);
        //startActivity(arrange_intent);
     }
     catch (Exception e){
         Log.v("myapp", "Main Activity Exception : "+e);
     }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        spinner.setVisibility(View.INVISIBLE);
        int id = item.getItemId();

        switch(id)
        {
            case R.id.settings : Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                break;

            case R.id.help : Intent help_intent = new Intent(this, HelpActivity.class);
                startActivity(help_intent);
                break;

            case R.id.about : Intent about_intent = new Intent(this, AboutActivity.class);
                startActivity(about_intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

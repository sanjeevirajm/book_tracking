package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.BreakIterator;

public class ModifyBookID extends AppCompatActivity {
    Spinner spinner_flag_bar_qr;
    String arrayFlagBarQr[] = {"Bar Code", "Qr Code"};
    String flag_bar_qr;
    public static Integer book_id, modify_flag_bar_qr = 0;
    public ArrayAdapter<String> adapterFlagBarQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_book_id);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
        spinner_flag_bar_qr = (Spinner) findViewById(R.id.spinner);

        adapterFlagBarQr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayFlagBarQr);
        spinner_flag_bar_qr.setAdapter(adapterFlagBarQr);

        spinner_flag_bar_qr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                flag_bar_qr = adapter.getItemAtPosition(position).toString();
                flag_bar_qr.trim();

                if(flag_bar_qr == arrayFlagBarQr[0])
                {
                    modify_flag_bar_qr = 1;
                }
                else if(flag_bar_qr == arrayFlagBarQr[1])
                {
                    modify_flag_bar_qr = 2;
                }

                Toast.makeText(getApplicationContext(), "Selected Format : " + modify_flag_bar_qr, Toast.LENGTH_SHORT).show();
                Log.v("myapp", "AddBookDetailsActivity Line: 7 : " + modify_flag_bar_qr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    catch (Exception e)
    {
        Log.v("myapp","Exception : "+e);
    }
 }
    public void showBookDetails(View v)
    {
        EditText edit_id = (EditText) findViewById(R.id.editText2);
        book_id = Integer.valueOf(String.valueOf(edit_id.getText()));
        Intent showBookDetails = new Intent(this, ModifyBookDetailsActivity.class);
        startActivity(showBookDetails);
    }

    public void cancelFunction(View v)
    {
        Intent cancelIntent = new Intent(this, ModifyActivity.class);
        startActivity(cancelIntent);
    }
}

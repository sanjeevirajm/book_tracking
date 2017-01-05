package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ArrangeRackDetails extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;

    public static Integer order_no, rack_no, shelf_no;


    Spinner spinner_rack_no, spinner_shelf_no, spinner_order_no;
    public Integer flag_bar_qr;
    public ArrayAdapter<Integer> adapterRackNo;
    public ArrayAdapter<Integer> adapterShelfNo;
    public ArrayAdapter<Integer> adapterOrderNo;

    public Integer total_no_of_racks = 0;
    public Integer total_no_of_shelves = 0;
    public Integer total_no_of_books = 0;
    public Integer start_rack_no, cur_rack_no, cur_book_no, adapterBookNo, start_shelf_no, cur_shelf_no, start_order_no, cur_order_no, selected_rack_no, selected_shelf_no, selected_book_no, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_arrange_rack_details);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            spinner_rack_no = (Spinner) findViewById(R.id.spinner4);
            spinner_shelf_no = (Spinner) findViewById(R.id.spinner5);
            spinner_order_no = (Spinner) findViewById(R.id.spinner6);


            sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);
            if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
                Log.v("myapp", " AddBookdetailsActivity Line: 1");
                IPValue = sharedpreferences.getString(IPAddress, "");
                UserNameValue = sharedpreferences.getString(UserName, "");
                PasswordValue = sharedpreferences.getString(Password, "");
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 2");
                startProgram();
            }
            else
            {
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 7");
                Toast.makeText(getApplicationContext(), "Enter IPAddress, Username, Password", Toast.LENGTH_LONG).show();
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 8");
                startActivity(settings_intent);
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 9");
            }
        }
        catch (Exception e)
        {
            Log.v("myapp", "ArrangeRackDetailsActivity Exception: "+e);
        }
    }

    public void startProgram() {
        try {
            new PHPGetNoOfRacks(getApplicationContext()).execute(IPValue);
            Log.v("myapp", "ArrangeRackDetailsActivity Line: 3");

            while (total_no_of_racks == 0) {
                if(PHPGetNoOfRacks.rack_no!=0) {
                    total_no_of_racks = PHPGetNoOfRacks.rack_no;
                    Log.v("myapp", "ArrangeRackDetailsActivity Line: 4 : " + total_no_of_racks);
                }
            }

            Integer[] array_no_of_racks = new Integer[total_no_of_racks];
            start_rack_no = 1;
            i = 0;

            for (cur_rack_no = start_rack_no; cur_rack_no <= total_no_of_racks; i++, cur_rack_no++) {
                array_no_of_racks[i] = cur_rack_no;
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 6 : " + array_no_of_racks[i]);
            }

            addRackNoToSpinner(array_no_of_racks);
            addShelfNoToSpinner(1);
            Integer not_needed[] = {1};
            addOrderNoToSpinner(not_needed);

        }
        catch (Exception e)
        {
            Log.v("myapp", "ArrangeRackDetailsActivity: Exception: "+e);
        }
    }


    public void addRackNoToSpinner(Integer arrayNoOfRacksSpinner[])
    {
        try {
            adapterRackNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, arrayNoOfRacksSpinner);
            spinner_rack_no.setAdapter(adapterRackNo);
            spinner_rack_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_rack_no = Integer.valueOf(String.valueOf(item));
                    rack_no = selected_rack_no;
                    Toast.makeText(getApplicationContext(), "Selected No : " + selected_rack_no, Toast.LENGTH_SHORT).show();
                    Log.v("myapp", "ArrangeRackDetailsActivity Line: 7 : " + selected_rack_no);

                    addShelfNoToSpinner(selected_rack_no);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        }
        catch (Exception e)
        {
            Log.v("myapp", "ArrangeRackDetailsActivity Exception: "+e);
        }
    }

    public void addShelfNoToSpinner(final Integer selectedRackNo)
    {
        try {
            total_no_of_shelves = 0;
            total_no_of_books = 0;
            new PHPGetNoOfShelves(getApplicationContext()).execute(IPValue, String.valueOf(selectedRackNo));
            new PHPGetNoOfBooks(getApplicationContext()).execute(IPValue, String.valueOf(selectedRackNo));

            Log.v("myapp", "ArrangeRackDetailsActivity Line: 3" +selectedRackNo);

            while (total_no_of_shelves == 0) {
                if(PHPGetNoOfShelves.shelf_no != -1)
                    total_no_of_shelves = PHPGetNoOfShelves.shelf_no;
                Log.v("myapp", "ArrangeRackDetailsActivity shelf Line: 4 : " + total_no_of_shelves);
            }

            while (PHPGetNoOfBooks.flag_book_no == 0) {
                total_no_of_books = PHPGetNoOfBooks.book_no;
                Log.v("myapp", "ArrangeRackDetailsActivity books Line: 4 : " + total_no_of_books);
            }

            PHPGetNoOfShelves.shelf_no = -1;
            PHPGetNoOfBooks.flag_book_no = 0;

            Integer[] array_no_of_shelves = new Integer[total_no_of_shelves];
            start_shelf_no = 1;
            i = 0;

            for (cur_shelf_no = start_shelf_no; cur_shelf_no <= total_no_of_shelves; i++, cur_shelf_no++) {
                array_no_of_shelves[i] = cur_shelf_no;
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 6 : " + array_no_of_shelves[i]);
            }

            final Integer[] array_no_of_books = new Integer[total_no_of_books];
            Integer start_book_no = 1;
            i = 0;
            for (cur_book_no = start_book_no; cur_book_no <= total_no_of_books; i++, cur_book_no++) {
                array_no_of_books[i] = cur_book_no;
                Log.v("myapp", "ArrangeRackDetailsActivity Line: 6 : " + array_no_of_books[i]);
            }
            addOrderNoToSpinner(array_no_of_books);

            adapterShelfNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, array_no_of_shelves);
            spinner_shelf_no.setAdapter(adapterShelfNo);
            spinner_shelf_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_shelf_no = Integer.valueOf(String.valueOf(item));
                    shelf_no = selected_shelf_no;
                    Toast.makeText(getApplicationContext(), "Selected No : " + selected_shelf_no, Toast.LENGTH_SHORT).show();

                    addOrderNoToSpinner(array_no_of_books);
                    Log.v("myapp", "ArrangeRackDetailsActivity Line: 7 : " + selected_shelf_no);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        }
        catch (Exception e)
        {
            Log.v("myapp", "ArrangeRackDetailsActivity Exception: "+e);
        }
    }


    public void addOrderNoToSpinner(Integer arrayNoOfBooksSpinner[])
    {
        try {
            adapterOrderNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, arrayNoOfBooksSpinner);
            spinner_order_no.setAdapter(adapterOrderNo);
            spinner_order_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_book_no = Integer.valueOf(String.valueOf(item));
                    order_no = selected_book_no;
                    Toast.makeText(getApplicationContext(), "Selected No : " + selected_book_no, Toast.LENGTH_SHORT).show();
                    Log.v("myapp", "ArrangeRackDetailsActivity Line: 7 : " + selected_book_no);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        }
        catch (Exception e)
        {
            Log.v("myapp", "ArrangeRackDetailsActivity Exception: "+e);
        }
    }

    public void arrange(View v)
    {
        try {

            Log.v("myapp", "ArrangeRackDetailsActivity Line: 8 : " + selected_book_no);
            Intent arrange_scan_intent = new Intent(this, ArrangeScanActivity.class);
            startActivity(arrange_scan_intent);


            Log.v("myapp", "ArrangeRackDetailsActivity Line: 8 : " + selected_book_no);




            Log.v("myapp", "ArrangeRackDetailsActivity Line: 10 : " + selected_book_no);
        }
        catch (Exception e){
            Log.v("myapp", "ArrangeRackDetailsActivity Line Exception:  : " + e);
        }
    }

    public void cancel(View view) {
        Intent cancel_intent = new Intent(this, ArrangeActivity.class);
        startActivity(cancel_intent);
    }
}

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

public class ModifyBookDetailsActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;

    EditText edit_id, edit_title, edit_author, edit_publication, edit_department, edit_cost;
    Integer book_cost, order_no, rack_no, shelf_no;
    String book_title, book_author, book_publication, book_department;
    Integer book_id = 0;
    Spinner spinner_rack_no, spinner_shelf_no, spinner_order_no;
    public Integer flag_bar_qr = 0;
    public Integer flag_bookid_barqrcode = 0;
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
            setContentView(R.layout.activity_modify_book_details);

            spinner_rack_no = (Spinner) findViewById(R.id.spinner1);
            spinner_shelf_no = (Spinner) findViewById(R.id.spinner2);
            spinner_order_no = (Spinner) findViewById(R.id.spinner3);

            edit_title = (EditText) findViewById(R.id.editText4);
            edit_author = (EditText) findViewById(R.id.editText5);
            edit_publication = (EditText) findViewById(R.id.editText6);
            edit_cost = (EditText) findViewById(R.id.editText7);
            edit_department = (EditText) findViewById(R.id.editText8);

            sharedpreferences = getSharedPreferences("settings_preferences", Context.MODE_PRIVATE);
            if (sharedpreferences.contains(IPAddress) && sharedpreferences.contains(UserName) && sharedpreferences.contains(Password)) {
                Log.v("myapp", " ModifyBookdetailsActivity Line: 1");
                IPValue = sharedpreferences.getString(IPAddress, "");
                UserNameValue = sharedpreferences.getString(UserName, "");
                PasswordValue = sharedpreferences.getString(Password, "");
                Log.v("myapp", "ModifyBookDetailsActivity Line: 2");
                startProgram();
            }
            else
            {
                Log.v("myapp", "ModifyBookDetailsActivity Line: 7");
                Toast.makeText(getApplicationContext(), "Enter IPAddress, Username, Password", Toast.LENGTH_LONG).show();
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                Log.v("myapp", "ModifyBookDetailsActivity Line: 8");
                startActivity(settings_intent);
                Log.v("myapp", "ModifyBookDetailsActivity Line: 9");
            }
        }
        catch (Exception e)
        {
            Log.v("myapp", "ModifyBookDetailsActivity Exception: "+e);
        }
    }

    public void startProgram() {
        try {
            while(flag_bookid_barqrcode == 0)
                flag_bookid_barqrcode = ModifyActivity.flag_bookid_barqrcode;

            if(flag_bookid_barqrcode == 1) {
                Log.v("myapp", "ModifyBookDetailsActivity Line: 11");
                while (flag_bar_qr == 0)
                    flag_bar_qr = ModifyBookID.modify_flag_bar_qr;
                while (book_id == 0)
                    book_id = ModifyBookID.book_id;
                Log.v("myapp", "ModifyBookDetailsActivity Line: 12 " + flag_bar_qr + "  " + book_id + "   " + PHPGetBookDetails.flag_details);
            }
            else{
                Log.v("myapp", "ModifyBookDetailsActivity Line: 11");
                while (flag_bar_qr == 0)
                    flag_bar_qr = ModifyActivity.flag_bar_qr;
                while (book_id == 0)
                    book_id = Integer.parseInt(ModifyActivity.contents.trim());
                Log.v("myapp", "ModifyBookDetailsActivity Line: 12 " + flag_bar_qr + "  " + book_id + "   " + PHPGetBookDetails.flag_details);
            }
            edit_id = (EditText) findViewById(R.id.editText);
            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +book_id );
            edit_id.setText("" + book_id);

            PHPGetBookDetails.flag_details = 0;

            new PHPGetBookDetails(getApplicationContext()).execute(IPValue, String.valueOf(flag_bar_qr), String.valueOf(book_id));
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");
            Log.v("myapp", "ModifyBookDetailsActivity Line: 3");



            while (PHPGetBookDetails.flag_details == 0)
            {
                Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : before while");
                if(PHPGetBookDetails.flag_details == 1) {
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 1");
                    book_title = PHPGetBookDetails.title;
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 2" + book_title);
                    book_author = PHPGetBookDetails.author;
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 3" + book_author);
                    book_department = PHPGetBookDetails.department;
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 4" + book_department);
                    book_publication = PHPGetBookDetails.publication;
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 5" + book_publication);

                    if(PHPGetBookDetails.cost == null)
                        book_cost = 0;
                    else
                        book_cost = Integer.parseInt(PHPGetBookDetails.cost.trim());

                    if(PHPGetBookDetails.rack_no == null)
                        rack_no = 0;
                    else
                        rack_no = Integer.parseInt(PHPGetBookDetails.rack_no);
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 6" + book_cost);

                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 7" + rack_no);
                    if(PHPGetBookDetails.shelf_no == null)
                        shelf_no = 0;
                    else
                        shelf_no = Integer.parseInt(PHPGetBookDetails.shelf_no);

                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 8" + shelf_no);
                    if(PHPGetBookDetails.order_no == null)
                        order_no = 0;
                    else
                        order_no = Integer.parseInt(PHPGetBookDetails.order_no);

                    Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : in while 9" + order_no);
                }
            }

            Log.v("myapp", "ModifyBookDetailsActivity Line: 3 after");

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +book_title );
            edit_title.setText(book_title);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " + book_author);
            edit_author.setText(book_author);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " + book_publication);
            edit_publication.setText(book_publication);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " + book_cost);
            edit_cost.setText(""+book_cost);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +book_department );
            edit_department.setText(book_department);

            new PHPGetNoOfRacks(getApplicationContext()).execute(IPValue);



            while (total_no_of_racks == 0) {
                if(PHPGetNoOfRacks.rack_no!=0) {
                    total_no_of_racks = PHPGetNoOfRacks.rack_no;
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 4 : " + total_no_of_racks);
                }
            }

            Integer[] array_no_of_racks = new Integer[total_no_of_racks];
            start_rack_no = 1;
            i = 0;

            for (cur_rack_no = start_rack_no; cur_rack_no <= total_no_of_racks; i++, cur_rack_no++) {
                array_no_of_racks[i] = cur_rack_no;
                Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : " + array_no_of_racks[i]);
            }
            Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : out");


            addRackNoToSpinner(array_no_of_racks);

        }
        catch (Exception e)
        {
            Log.v("myapp", "ModifyBookDetailsActivity: Exception: "+e);
        }
    }


    public void addRackNoToSpinner(Integer arrayNoOfRacksSpinner[])
    {
        try {
            adapterRackNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, arrayNoOfRacksSpinner);
            spinner_rack_no.setAdapter(adapterRackNo);
            spinner_rack_no.setSelection(adapterRackNo.getPosition(rack_no));
            spinner_rack_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_rack_no = Integer.parseInt(item);
                  //  Toast.makeText(getApplicationContext(), "Selected No : " + selected_rack_no, Toast.LENGTH_SHORT).show();
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 7 : " + selected_rack_no);

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
            Log.v("myapp", "ModifyBookDetailsActivity Exception: "+e);
        }
    }

    public void addShelfNoToSpinner(Integer selectedRackNo)
    {
        try {
            total_no_of_shelves = 0;
            total_no_of_books = 0;
            new PHPGetNoOfShelves(getApplicationContext()).execute(IPValue, String.valueOf(selectedRackNo));
            new PHPGetNoOfBooks(getApplicationContext()).execute(IPValue, String.valueOf(selectedRackNo));

            Log.v("myapp", "ModifyBookDetailsActivity Line: 3" + selectedRackNo);

            while (total_no_of_shelves == 0) {
                if(PHPGetNoOfShelves.shelf_no != -1)
                    total_no_of_shelves = PHPGetNoOfShelves.shelf_no;
                Log.v("myapp", "ModifyBookDetailsActivity shelf Line: 4 : " + total_no_of_shelves);
            }

            while (total_no_of_books == 0) {
                if(PHPGetNoOfBooks.book_no != -1)
                    total_no_of_books = PHPGetNoOfBooks.book_no;
                Log.v("myapp", "ModifyBookDetailsActivity books Line: 4 : " + total_no_of_books);
            }

            PHPGetNoOfShelves.shelf_no = -1;
            PHPGetNoOfBooks.book_no = -1;

            Integer[] array_no_of_shelves = new Integer[total_no_of_shelves];
            start_shelf_no = 1;
            i = 0;

            for (cur_shelf_no = start_shelf_no; cur_shelf_no <= total_no_of_shelves; i++, cur_shelf_no++) {
                array_no_of_shelves[i] = cur_shelf_no;
                Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : " + array_no_of_shelves[i]);
            }

            final Integer[] array_no_of_books = new Integer[total_no_of_books];
            int start_book_no = 1;
            i = 0;
            for (cur_book_no = start_book_no; cur_book_no <= total_no_of_books; i++, cur_book_no++) {
                array_no_of_books[i] = cur_book_no;
                Log.v("myapp", "ModifyBookDetailsActivity Line: 6 : " + array_no_of_books[i]);
            }
            addOrderNoToSpinner(array_no_of_books);

            adapterShelfNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, array_no_of_shelves);
            spinner_shelf_no.setAdapter(adapterShelfNo);
            spinner_shelf_no.setSelection(adapterShelfNo.getPosition(shelf_no));
            spinner_shelf_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_shelf_no = Integer.parseInt(item);
                   // Toast.makeText(getApplicationContext(), "Selected No : " + selected_shelf_no, Toast.LENGTH_SHORT).show();

                    addOrderNoToSpinner(array_no_of_books);
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 7 : " + selected_shelf_no);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        }
        catch (Exception e)
        {
            Log.v("myapp", "ModifyBookDetailsActivity Exception: "+e);
        }
    }


    public void addOrderNoToSpinner(Integer arrayNoOfBooksSpinner[])
    {
        try {
            adapterOrderNo = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, arrayNoOfBooksSpinner);
            spinner_order_no.setAdapter(adapterOrderNo);
            spinner_order_no.setSelection(adapterOrderNo.getPosition(order_no));
            spinner_order_no.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                                           int position, long id) {

                    String item = adapter.getItemAtPosition(position).toString();
                    item.trim();

                    selected_book_no = Integer.valueOf(String.valueOf(item));
                  //  Toast.makeText(getApplicationContext(), "Selected No : " + selected_book_no, Toast.LENGTH_SHORT).show();
                    Log.v("myapp", "ModifyBookDetailsActivity Line: 7 : " + selected_book_no);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        }
        catch (Exception e)
        {
            Log.v("myapp", "ModifyBookDetailsActivity Exception: "+e);
        }
    }

    public void addBookDetails(View v)
    {
        try {

            Log.v("myapp", "ModifyBookDetailsActivity Line: 8 : " + selected_book_no);
            String string_flag, string_book_id, string_rack_no, string_shelf_no, string_order_no, string_book_cost;
            //book_id, rack_no, shelf_no, order_no;


            Log.v("myapp", "ModifyBookDetailsActivity Line: 8 : " + selected_book_no);

            book_title = String.valueOf(edit_title.getText());
            book_title.trim();
            book_author = String.valueOf(edit_author.getText());
            book_author.trim();
            book_publication = String.valueOf(edit_publication.getText());
            book_publication.trim();
            book_department = String.valueOf(edit_department.getText());
            book_department.trim();
            book_cost = Integer.valueOf(String.valueOf(edit_cost.getText()));

            string_flag = String.valueOf(flag_bar_qr);
            string_flag.trim();
            string_book_id = String.valueOf(book_id);
            string_book_id.trim();
            string_rack_no = String.valueOf(selected_rack_no);
            string_rack_no.trim();
            string_shelf_no = String.valueOf(selected_shelf_no);
            string_shelf_no.trim();
            string_order_no = String.valueOf(selected_book_no);
            string_order_no.trim();
            string_book_cost = String.valueOf(book_cost);
            string_book_cost.trim();

            Log.v("myapp", "ModifyBookDetailsActivity Line: 9 : " + selected_book_no);

            new PHPModifyDetails(getApplicationContext()).execute(IPValue, string_flag, string_book_id, string_rack_no, string_shelf_no, string_order_no, book_title, book_author, book_publication, book_department, string_book_cost);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 10 : " + selected_book_no);
        }
        catch (Exception e){
            Log.v("myapp", "ModifyBookDetailsActivity Line Exception:  : " + e);
        }
    }

    public void cancel(View view) {
        Intent cancel_intent = new Intent(this, ModifyActivity.class);
        startActivity(cancel_intent);
    }
}

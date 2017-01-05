package com.apps.sanjeeviraj.bookfiletracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchShowBookDetailsActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String IPAddress = "ip";
    public static final String UserName = "uname";
    public static final String Password = "pwd";
    public String IPValue, UserNameValue, PasswordValue;

    EditText edit_id, edit_title, edit_author, edit_publication, edit_department, edit_cost, edit_rackno, edit_shelfno, edit_bookno;
    Integer book_cost, order_no, rack_no, shelf_no;
    String book_title, book_author, book_publication, book_department;
    Integer book_id = 0;
    Spinner spinner_rack_no, spinner_shelf_no, spinner_order_no;
    public Integer flag_bar_qr = 0;
    public Integer flag_bookid_barqrcode = 0;


    public Integer total_no_of_racks = 0;
    public Integer total_no_of_shelves = 0;
    public Integer total_no_of_books = 0;
    public Integer start_rack_no, cur_rack_no, cur_book_no, adapterBookNo, start_shelf_no, cur_shelf_no, start_order_no, cur_order_no, selected_rack_no, selected_shelf_no, selected_book_no, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_show_book_details);


            edit_title = (EditText) findViewById(R.id.editText4);
            edit_author = (EditText) findViewById(R.id.editText5);
            edit_publication = (EditText) findViewById(R.id.editText6);
            edit_cost = (EditText) findViewById(R.id.editText7);
            edit_department = (EditText) findViewById(R.id.editText8);
            edit_rackno = (EditText) findViewById(R.id.editText9);
            edit_shelfno = (EditText) findViewById(R.id.editText10);
            edit_bookno = (EditText) findViewById(R.id.editText11);

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
                flag_bookid_barqrcode = SearchActivity.flag_bookid_barqrcode;

            if(flag_bookid_barqrcode == 1) {
                Log.v("myapp", "ModifyBookDetailsActivity Line: 11");
                while (flag_bar_qr == 0)
                    flag_bar_qr = SearchBookIDActivity.modify_flag_bar_qr;
                while (book_id == 0)
                    book_id = SearchBookIDActivity.book_id;
                Log.v("myapp", "ModifyBookDetailsActivity Line: 12 " + flag_bar_qr + "  " + book_id + "   " + PHPGetBookDetails.flag_details);
            }
            else if(flag_bookid_barqrcode == 2){
                Log.v("myapp", "ModifyBookDetailsActivity Line: 11");
                while (flag_bar_qr == 0)
                    flag_bar_qr = SearchActivity.flag_bar_qr;
                while (book_id == 0)
                    book_id = Integer.parseInt(SearchActivity.contents.trim());
                Log.v("myapp", "ModifyBookDetailsActivity Line: 12 " + flag_bar_qr + "  " + book_id + "   " + PHPGetBookDetails.flag_details);
            }
            /*else {
                    //Aprama Papom
            }*/
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

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +rack_no );
            edit_rackno.setText(""+rack_no);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +shelf_no );
            edit_shelfno.setText(""+shelf_no);

            Log.v("myapp", "ModifyBookDetailsActivity Line: 14 " +order_no );
            edit_bookno.setText(""+order_no);


        }
        catch (Exception e)
        {
            Log.v("myapp", "ModifyBookDetailsActivity: Exception: "+e);
        }
    }

    public void cancel(View view) {
        Intent cancel_intent = new Intent(this, SearchActivity.class);
        startActivity(cancel_intent);
    }
}

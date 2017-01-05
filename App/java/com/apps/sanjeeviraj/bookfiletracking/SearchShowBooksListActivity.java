package com.apps.sanjeeviraj.bookfiletracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchShowBooksListActivity extends AppCompatActivity {
    String[] mobileArray = {"Title: ","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try
        {
            super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_show_books_list);
      //  mobileArray[0] = mobileArray[0]+"C Programming";
        //mobileArray[0] = mobileArray[0]+"\n Author: ";
        //mobileArray[0] = mobileArray[0]+"Edwin Dayanad";
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_search_show_books_list, mobileArray);

        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(adapter);
        }
        catch (Exception e){
            Log.v("myapp", "Main Activity Exception : " + e);
        }
    }
}

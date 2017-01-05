package com.apps.sanjeeviraj.bookfiletracking;

/**
 * Created by sanje on 2/24/2016.
 */

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.util.Log;

public class PHPGetBookDetails  extends AsyncTask<String,Void,String> {

    private Context context;
    public static  Integer flag_details = 0;
    String IP, username, password, link, flag_bar_qr, book_id;
    public static String title, rack_no, order_no, shelf_no, publication, cost, department, author;
    String result = null;
    String arrayBookDetails[] = {"null","null","null","null","null","null","null","null"};

    public PHPGetBookDetails(Context context) {
        this.context = context;

    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            Log.v("myapp", " Before assigning values in PHPGetBookDetailsActivity " );
            IP = (String)arg0[0];
            Log.v("myapp", " After assigning value1 in PHPGetBookDetailsActivity " );
            flag_bar_qr = (String)arg0[1];
            Log.v("myapp", " After assigning value2 in PHPGetBookDetailsActivity " );
            book_id = (String)arg0[2];
            Log.v("myapp", " After assigning value3 in PHPGetBookDetailsActivity " );

            link="http://"+IP+"/book_tracking/modify/get_book_details.php";
            Log.v("myapp", "PHP URL : " + link);

            String data  = "flag_bar_qr"+"="+flag_bar_qr;
            data += "&"+"book_id"+"="+book_id;
            Log.v("myapp", " After assigning data in PHPGetBookDetailsActivity " );

            URL url = new URL(link);
            Log.v("myapp", " Before url openconnection in PHPGetBookDetailsActivity ");
            URLConnection conn = url.openConnection();
            Log.v("myapp", " After url openconnection in PHPGetBookDetailsActivity ");

            conn.setDoOutput(true);
            Log.v("myapp", " Before outstream object in PHPGetBookDetailsActivity ");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            Log.v("myapp", " Before write data in PHPGetBookDetailsActivity ");
            wr.write(data);
            Log.v("myapp", " Before flush in PHPGetBookDetailsActivity ");
            wr.flush();

            Log.v("myapp", " Before buff read in PHPGetBookDetailsActivity ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            Log.v("myapp", " Before sb in PHPGetBookDetailsActivity " );
            StringBuilder sb = new StringBuilder();
            Log.v("myapp", " Before string line in PHPGetBookDetailsActivity " );
            String line;

            Log.v("myapp", " Before while string loop in PHPGetBookDetailsActivity " );
            Integer i = 0;

            while (i<8)
            {



                arrayBookDetails[i] = reader.readLine();
                //arrayBookDetails[i].trim();
                Log.v("myapp","sb while : "+arrayBookDetails[i]);
                i++;
            }


            rack_no = arrayBookDetails[0];
            shelf_no = arrayBookDetails[1];
            order_no = arrayBookDetails[2];
            title = arrayBookDetails[3];
            author = arrayBookDetails[4];
            publication = arrayBookDetails[5];
            department = arrayBookDetails[6];
            cost = arrayBookDetails[7].trim();

            flag_details = 1;
            Log.v("myapp", " Before resultInt " );
            Log.v("myapp", " After resultInt " );
            return arrayBookDetails[i];
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){


    }
}

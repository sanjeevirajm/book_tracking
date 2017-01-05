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

public class PHPGetBookID  extends AsyncTask<String,Void,String> {

    private Context context;
    public static Integer get_book_id = 0;
    public static  Integer flag_details = 0;
    String IP, username, password, link, flag_bar_qr, book_id;
    public  String rack_no, shelf_no, order_no;
    public String result = null;

    public PHPGetBookID(Context context) {
        this.context = context;

    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            Log.v("myapp", " Before assigning values in PHPGetBookIDActivity " );
            IP = (String)arg0[0];
            Log.v("myapp", " After assigning value1 in PHPGetBookIDActivity " );
            flag_bar_qr = (String)arg0[1];
            Log.v("myapp", " After assigning value2 in PHPGetBookIDActivity " );
            rack_no = (String)arg0[2];
            Log.v("myapp", " After assigning value3 in PHPGetBookIDActivity " );
            shelf_no = (String)arg0[3];
            Log.v("myapp", " After assigning value3 in PHPGetBookIDActivity " );
            order_no = (String)arg0[4];
            Log.v("myapp", " After assigning value3 in PHPGetBookIDActivity " );

            link="http://"+IP+"/book_tracking/arrange/get_book_id.php";
            Log.v("myapp", "PHP URL : " + link);

            String data  = "flag_bar_qr"+"="+flag_bar_qr;
            data += "&"+"rack_no"+"="+rack_no;
            data += "&"+"shelf_no"+"="+shelf_no;
            data += "&"+"order_no"+"="+order_no;
            Log.v("myapp", " After assigning data in PHPGetBookIDActivity ");

            URL url = new URL(link);
            Log.v("myapp", " Before url openconnection in PHPGetBookIDActivity ");
            URLConnection conn = url.openConnection();
            Log.v("myapp", " After url openconnection in PHPGetBookIDActivity ");

            conn.setDoOutput(true);
            Log.v("myapp", " Before outstream object in PHPGetBookIDActivity ");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            Log.v("myapp", " Before write data in PHPGetBookIDActivity ");
            wr.write(data);
            Log.v("myapp", " Before flush in PHPGetBookIDActivity ");
            wr.flush();

            Log.v("myapp", " Before buff read in PHPGetBookIDActivity ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            Log.v("myapp", " Before sb in PHPGetBookIDActivity " );
            StringBuilder sb = new StringBuilder();
            Log.v("myapp", " Before string line in PHPGetBookIDActivity " );
            String line = null;
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                Log.v("myapp","sb while : "+sb);
                break;
            }
            result = sb.toString();
            result = result.trim();
            get_book_id = Integer.parseInt(result);
            Log.v("myapp", " Before while string loop in PHPGetBookIDActivity " );

            return result;
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        Log.v("myapp", " Before resultInt " );

        flag_details = 1;

        Log.v("myapp", " After resultInt " );

    }
}

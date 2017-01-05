package com.apps.sanjeeviraj.bookfiletracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class PHPGetNoOfBooks  extends AsyncTask<String,Void,String>{

    private Context context;
    public String IP, link, rack_no;
    public String result = null;
    public static int book_no = -1;
    public static int flag_book_no = 0;

    public PHPGetNoOfBooks(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            Log.v("myapp", " inside PHPGetRacksActivity line: 1" );
            IP = (String)arg0[0];
            rack_no = (String)arg0[1];

            Log.v("myapp", " inside PHPGetRacksActivity line: 2" +IP);

            link="http://"+IP+"/book_tracking/book_add/get_no_of_books.php";
            Log.v("myapp", " inside PHPGetRacksActivity line: 3" +link );

            String data  = "rack_no"+"="+rack_no;

            Log.v("myapp", " inside PHPGetRacksActivity line: 4" );

            URL url = new URL(link);
            Log.v("myapp", " inside PHPGetRacksActivity line: 5" );
            URLConnection conn = url.openConnection();
            Log.v("myapp", " inside PHPGetRacksActivity line: 6" );

            conn.setDoOutput(true);
            Log.v("myapp", " inside PHPGetRacksActivity line: 7" );
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            Log.v("myapp", " inside PHPGetRacksActivity line: 8" );
            wr.write(data);
            Log.v("myapp", " inside PHPGetRacksActivity line: 9" );
            wr.flush();

            Log.v("myapp", " inside PHPGetRacksActivity line: 10" );
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            Log.v("myapp", " inside PHPGetRacksActivity line: 11" );
            StringBuilder sb = new StringBuilder();
            Log.v("myapp", " inside PHPGetRacksActivity line: 12" );
            String line;

            Log.v("myapp", " inside PHPGetRacksActivity line: 13" );
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                Log.v("myapp","sb while : "+sb);
                break;
            }
            result = sb.toString();
            flag_book_no = 1;

            Log.v("myapp", " inside PHPGetRacksActivity line: 14" +result);
            result = result.trim();
            book_no = Integer.parseInt(result);
            return result;
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        if(book_no == 0) {
            Toast.makeText(context, "Enter no of books", Toast.LENGTH_SHORT).show();
            Log.v("myapp", " Before openSettingsActivity() ");
            openSettingsActivity();
        }

    }

    private void openSettingsActivity() {
        try {
            Log.v("myapp", " inside open settings function ");
            Log.v("myapp", " after view initialization ");
            Intent settings_intent = new Intent(context, SettingsActivity.class);
            Log.v("myapp", " before startActivity ");
            settings_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(settings_intent);
            Log.v("myapp", " after startActivity ");
        }
        catch (Exception e) {
            Log.v("myapp", " after startActivity exception " +e);
        }
    }

}

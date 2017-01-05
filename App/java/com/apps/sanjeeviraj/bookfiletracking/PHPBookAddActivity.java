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
import android.view.View;
import android.widget.Toast;

public class PHPBookAddActivity  extends AsyncTask<String,Void,String>{

    /* //book_id, rack_no, shelf_no, order_no;
        book_title = String.valueOf(edit_title.getText());
        book_author = String.valueOf(edit_author.getText());
        book_publication = String.valueOf(edit_publication.getText());
        book_department = String.valueOf(edit_department.getText());
        book_cost = Integer.valueOf(String.valueOf(edit_title.getText()));
        */
    private Context context;
    String IP,link, book_title, book_author, book_publication, book_department;
    String result = null;
    Integer resultInt, flag_bar_qr, book_id, rack_no, shelf_no, order_no, book_cost;
    String inserted_result="1 ";
    String error_result="2 ";
    public PHPBookAddActivity(Context context) {
        this.context = context;

    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            Log.v("myapp", " Before assigning values in PHPConnectActivity " );
            IP = (String)arg0[0];
            flag_bar_qr = Integer.parseInt((String)arg0[1]);
            book_id = Integer.parseInt((String)arg0[2]);
            rack_no = Integer.parseInt((String)arg0[3]);
            shelf_no = Integer.parseInt((String)arg0[4]);
            order_no = Integer.parseInt((String)arg0[5]);
            book_title = (String)arg0[6];
            book_author = (String)arg0[7];
            book_publication = (String)arg0[8];
            book_department = (String)arg0[9];
            book_cost =Integer.parseInt((String)arg0[10]);

            if(flag_bar_qr == 1)
                link="http://"+IP+"/book_tracking/book_add/bar_book_add.php";
            else
                link="http://"+IP+"/book_tracking/book_add/qr_book_add.php";

            Log.v("myapp", "PHP URL : " + link);

            String data  = "book_id"+"="+book_id;
            data += "&"+"rack_no"+"="+rack_no;
            data += "&"+"shelf_no"+"="+shelf_no;
            data += "&"+"order_no"+"="+order_no;
            data += "&"+"title"+"="+book_title;
            data += "&"+"author"+"="+book_author;
            data += "&"+"publication"+"="+book_publication;
            data += "&"+"department"+"="+book_department;
            data += "&"+"cost"+"="+book_cost;

            Log.v("myapp", " After assigning data in PHPConnectActivity " );

            URL url = new URL(link);
            Log.v("myapp", " Before url openconnection in PHPConnectActivity " );
            URLConnection conn = url.openConnection();
            Log.v("myapp", " After url openconnection in PHPConnectActivity " );

            conn.setDoOutput(true);
            Log.v("myapp", " Before outstream object in PHPConnectActivity ");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            Log.v("myapp", " Before write data in PHPConnectActivity " );
            wr.write(data);
            Log.v("myapp", " Before flush in PHPConnectActivity ");
            wr.flush();

            Log.v("myapp", " Before buff read in PHPConnectActivity ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            Log.v("myapp", " Before sb in PHPConnectActivity " );
            StringBuilder sb = new StringBuilder();
            Log.v("myapp", " Before string line in PHPConnectActivity " );
            String line = null;

            Log.v("myapp", " Before while string loop in PHPConnectActivity " );
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                Log.v("myapp","sb while : "+sb);
                break;
            }
            result = sb.toString();
            Log.v("myapp", " After resultInt " );
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
            Toast.makeText(context, "Book Added", Toast.LENGTH_SHORT).show();
            Log.v("myapp", " Before openSettingsActivity() ");
            openPreviousActivity();
    }

    private void openPreviousActivity() {
        try {
            Log.v("myapp", " inside open previous activity function ");
            Intent previous_intent = new Intent(context, BookAddActivity.class);
            Log.v("myapp", " before startActivity ");
            previous_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(previous_intent);
            Log.v("myapp", " after startActivity ");
        }
        catch (Exception e) {
            Log.v("myapp", " after startActivity exception " +e);
        }
    }

    private void openMainActivity() {
        try {
            Log.v("myapp", " inside open main function ");
            Log.v("myapp", " after view initialization ");
            Intent main_intent = new Intent(context, MainActivity.class);
            Log.v("myapp", " before startActivity ");
            main_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(main_intent);
            Log.v("myapp", " after startActivity ");
        }
        catch (Exception e) {
            Log.v("myapp", " after startActivity exception " +e);
        }
    }
}

    /* public void openSettingsActivity(View v)    {
        Intent settings_intent = new Intent(v.getContext(), SettingsActivity.class);
        v.getContext().startActivity(settings_intent);

    } */

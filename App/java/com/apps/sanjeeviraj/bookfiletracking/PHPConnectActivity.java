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
import android.widget.TextView;
import android.widget.Toast;

public class PHPConnectActivity  extends AsyncTask<String,Void,String>{

    private Context context;
    String IP, username, password, link;
    String result = null;
    Integer resultInt;
    String database_error_result = "0 ";
    String connected_result = "1 ";
    String error_result="2 ";
    public PHPConnectActivity(Context context) {
        this.context = context;

    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
            try{
                Log.v("myapp", " Before assigning values in PHPConnectActivity " );
                IP = (String)arg0[0];
                Log.v("myapp", " After assigning value1 in PHPConnectActivity " );
                username = (String)arg0[1];
                Log.v("myapp", " After assigning value2 in PHPConnectActivity " );
                password = (String)arg0[2];
                Log.v("myapp", " After assigning value3 in PHPConnectActivity " );

                link="http://"+IP+"/book_tracking/common/check_connection.php";
                Log.v("myapp", "PHP URL : " + link);

                String data  = "username"+"="+username;
                data += "&"+"password"+"="+password;
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
                Log.v("myapp", " Before resultInt " );
                //resultInt = Integer.parseInt(sb.toString());
                Log.v("myapp", " After resultInt " );
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

    @Override
    protected void onPostExecute(String result){
        try {
            resultInt = result.compareTo(error_result);
            Log.v("myapp", " Before resultInt check result string :" + result);
            Log.v("myapp", " Before resultInt check error result string :" + error_result);
            Log.v("myapp", " Before resultInt check Integer :" + resultInt);
            if (resultInt == 0) {
                MainActivity.spinner.setVisibility(View.INVISIBLE);
                Toast.makeText(context, "Enter correct username and password", Toast.LENGTH_SHORT).show();
                Log.v("myapp", " Before openSettingsActivity() ");
                openSettingsActivity();
                Log.v("myapp", " After resultInt check ");
            } else if (resultInt != 0) {
                MainActivity.spinner.setVisibility(View.INVISIBLE);
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                Log.v("myapp", " After resultInt check ");

            }
        }
        catch (Exception e){
            Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
            Log.v("myapp","Error: "+e);
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

    /* public void openSettingsActivity(View v)    {
        Intent settings_intent = new Intent(v.getContext(), SettingsActivity.class);
        v.getContext().startActivity(settings_intent);

    } */

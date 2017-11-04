package com.android.gifts.bottomnavigation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by slaYer on 1/6/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, List<String>> {
    Context context;
    AlertDialog alertDialog;
    List<String> data;
    String type;
    BackgroundWorker(Context c){
        context = c;
    }

    @Override
    protected List<String> doInBackground(String... params) {

        type = params[0];

        if(type.equals("login")){
            String login_url = "http://texephyr.com/tex/login2.php";
            try{
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                data = new ArrayList<String>();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    data.add(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return data;
            }catch(Exception e){ e.printStackTrace(); }
        }
        else if(type.equals("register")){
            String registration_url = "http://texephyr.com/tex/registration.php";
            try{
                String v_id = params[1];
                String visit_id = params[2];
                String name = params[3];
                String college = params[4];
                String event = params[5];
                String phone = params[6];
                String email = params[7];
                String total = params[8];
                String paid = params[9];
                String transactionId = params[10];
                URL url = new URL(registration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("v_id", "UTF-8") + "=" + URLEncoder.encode(v_id, "UTF-8")+"&"+
                        URLEncoder.encode("visit_id", "UTF-8") + "=" + URLEncoder.encode(visit_id, "UTF-8")+"&"+
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("college", "UTF-8") + "=" + URLEncoder.encode(college, "UTF-8")+"&"+
                        URLEncoder.encode("event", "UTF-8") + "=" + URLEncoder.encode(event, "UTF-8")+"&"+
                        URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(total, "UTF-8")+"&"+
                        URLEncoder.encode("paid", "UTF-8") + "=" + URLEncoder.encode(paid, "UTF-8")+"&"+
                        URLEncoder.encode("transactionId", "UTF-8") + "=" + URLEncoder.encode(transactionId, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                data = new ArrayList<String>();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    data.add(line);
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return data;

            }catch(Exception e){ e.printStackTrace(); }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(List<String> data) {
        if(type.equals("login")) {
            if (data.get(0).equals("1")) {
                Intent intent = new Intent(context, RegistrationActivity.class);
                intent.putStringArrayListExtra("data", (ArrayList<String>) data);
                context.startActivity(intent);

            } else {
                alertDialog.setMessage("Login Failed!");
                alertDialog.show();
            }
        }

        else if(type.equals("register")){

            if (data.get(0).equals("1")){

                alertDialog.setMessage("Registration Succesful!");
                alertDialog.show();
                RegistrationActivity r = (RegistrationActivity) context;
                r.clear();


            }  else {
                alertDialog.setMessage("Registration Failed");
                alertDialog.show();

            }

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

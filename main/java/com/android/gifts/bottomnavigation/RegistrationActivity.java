package com.android.gifts.bottomnavigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends Activity {

    Spinner college, event;
    TextView volunteer_name;
    EditText name, phone, email, total, paid, transactionId;
    ToggleButton paymentButton;
    String volunteer, v_id, visit_id;
    List<String> colleges, events;
    List<Integer> fees;
    Context context;
    boolean onlinePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        college = (Spinner) findViewById(R.id.college);
        event = (Spinner) findViewById(R.id.event);
        volunteer_name = (TextView) findViewById(R.id.volunteer_name);

        paymentButton = (ToggleButton) findViewById(R.id.paymentButton);
        transactionId = (EditText) findViewById(R.id.transactionId);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        total = (EditText) findViewById(R.id.total);
        paid = (EditText) findViewById(R.id.paid);

        ArrayList<String> data = getIntent().getStringArrayListExtra("data");

        colleges = new ArrayList<String>();
        events = new ArrayList<String>();
        fees = new ArrayList<Integer>();

        volunteer = data.get(1);
        v_id = data.get(2);
        visit_id = data.get(3);

        int index = 4;
        onlinePayment = false;

        while(!data.get(index).equals("events")) {
            colleges.add(data.get(index));
            index++;
        }

        index++;

        while(index < data.size()) {
            events.add(data.get(index));
            index++;
            fees.add(Integer.parseInt(data.get(index)));
            index++;
        }

        paymentButton.setChecked(false);
        transactionId.setEnabled(false);

        ArrayAdapter<String> collegesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colleges);
        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, events);

        volunteer_name.setText("Volunteer : " + volunteer);
        college.setAdapter(collegesAdapter);
        event.setAdapter(eventsAdapter);

        event.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(fees.size() > i)
                    total.setText(fees.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        paymentButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Paytm Barcode");

                    WebView wv = new WebView(context);
                    wv.loadUrl("http://texephyr.com/download.png");
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);

                            return true;
                        }
                    });

                    alert.setView(wv);
                    alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                    transactionId.setEnabled(true);
                }
                else{
                    transactionId.setEnabled(false);
                    transactionId.setText("");
                }
            }
        });

    }

    public void clear(){
        name.setText("");
        phone.setText("");
        email.setText("");
        total.setText("");
        paid.setText("");
    }

    public void onRegister(View view){
        String name = this.name.getText().toString();
        String phone = this.phone.getText().toString();
        String email = this.email.getText().toString();
        String total = this.total.getText().toString();
        String paid = this.paid.getText().toString();
        String type = "register";
        String transactionId;
        if(this.paymentButton.isChecked())
            transactionId = this.transactionId.getText().toString();
        else
            transactionId = "0000";

        if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || total.isEmpty() ||
                paid.isEmpty() || transactionId.isEmpty() ||
                (Integer.parseInt(paid)) > (Integer.parseInt(total))){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Fields empty or invalid");
            alertDialog.show();
        }
        else{
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, v_id, visit_id, name, college.getSelectedItem().toString(),
                    event.getSelectedItem().toString(), phone, email, total, paid, transactionId);

        }
    }
}

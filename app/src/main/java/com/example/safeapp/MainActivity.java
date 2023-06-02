package com.example.safeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;


public class MainActivity extends AppCompatActivity {

    private Button button_911;
    private Button button_112;
    private Button button_hT;
    private Menu mMenu;
    private String message911 = "911 HELP! I AM IN DANGER!";
    private String message112 = "HELP ME! I NEED EMERGENCY ASSISTANCE ASAP!";
    private String messageHT = "I AM IN NEED OF SHELTER FROM HUMAN TRAFFICKING HOTLINE!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_911 = findViewById(R.id.button911);
        button_112 = findViewById(R.id.button112);
        button_hT = findViewById(R.id.buttonHT);

        button_911.setOnClickListener(this::on911Click);
        button_112.setOnClickListener(this::on112Click);
        button_hT.setOnClickListener(this::onHtClick);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.context_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == R.id.location){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            Toast.makeText(this,"This activity page will display a map and have various size pins based on how many cases were reported", Toast.LENGTH_LONG).show();
            Toast.makeText(this,"You will be able to scroll and pinch and zoom on this activity page", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(item.getItemId() == R.id.moreInfo){
            Intent intent = new Intent(this,moreInfo.class);
            startActivity(intent);
            //Toast.makeText(this, "This activity page will display more information about the incident each victim reported", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId() == R.id.about){
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
            //Toast.makeText(this,"This activity page will display what our app is about and the SDG goal it is connected to", Toast.LENGTH_LONG).show();
            return true;
        }
        return true;
    }

    private void on911Click(View view){
        // call 911
        // drop a pin on location

        // Ask for permissions to be able to send the automated text message
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                // call method to send the automated text message
                send911message();

            }else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
    }

    private void on112Click(View view){
        // call 112
        // drop a pin on location



        // Ask for permissions to be able to send the automated text message
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                // call method to send the automated text message
                send112message();

            }else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
    }

    private void onHtClick(View view){
        // call human trafficking
        // drop a pin on location


        // Ask for permissions to be able to send the automated text message
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                // call method to send the automated text message
                sendHTmessage();

            }else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void send911message(){
        sendMessage("911", message911);
    }
    public void send112message(){
        sendMessage("112", message112);
    }
    public void sendHTmessage(){
        sendMessage("8883737888", messageHT);
    }
    private void sendMessage(String phoneNumber, String message){
        //Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setData(Uri.parse("smsto:" + phoneNumber));
        //intent.putExtra("sms_body", message);
        //intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_TEXT, message);
        //startActivity(intent);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "Message is sent! Go to the Text Messaging app to View", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_LONG).show();
        }
    }

    /*private void sendSMS(){
        // send a automated text
        String phoneNo = "tel:8137481264";
        String SMS = "I need help";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, SMS, null, null);
            Toast.makeText(this, "Message is sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to send!", Toast.LENGTH_LONG).show();
        }
    }*/


}

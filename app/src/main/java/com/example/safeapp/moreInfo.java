package com.example.safeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class moreInfo extends AppCompatActivity {
    private View view;
    private TextView textView;
   // private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        textView = findViewById(R.id.textView2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        EditText myEditText =  (EditText) findViewById(R.id.text_input);
        String text = myEditText.getText().toString();
        textView.setText(text);
        myEditText.getText().clear();
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
        Log.d("BUTTONS", "User clicked the SEND button");
    }
    });
    }

}
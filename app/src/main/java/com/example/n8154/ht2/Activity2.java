package com.example.n8154.ht2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    EditText editname;
    EditText editemail;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView) findViewById(R.id.textView);
        editname = (EditText) findViewById(R.id.editText3);
        editemail = (EditText) findViewById(R.id.editText2);
    }

    public void buttonPress(View v) { //Save username and email and add them to Shared Preferences
        text.setText("nimi: " + editname.getText().toString() + "\nemail: " + editemail.getText().toString());
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", editname.getText().toString());
        editor.apply();

    }

}

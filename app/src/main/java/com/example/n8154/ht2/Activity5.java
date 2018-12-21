package com.example.n8154.ht2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class Activity5 extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    ArrayList<Varaus> resArray = Dataholder.getInstance().reservationlist;
    Varaushallinta gyms;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    Button button;
    TextView text;
    Button button2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        edit1 = (EditText) findViewById(R.id.editDate);
        edit2 = (EditText) findViewById(R.id.editTime);
        edit3 = (EditText) findViewById(R.id.editReason);
        button = findViewById(R.id.saveButton);
        text = (TextView) findViewById(R.id.textView);

        spinner = (Spinner) findViewById(R.id.gymSpinner);
        gyms = new Varaushallinta();
        ArrayList<Sali> arr = gyms.mygyms(); // Get list of gym names from Varaushallinta
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); // Show gym names in spinner

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                } //;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2 = (Spinner) findViewById(R.id.resSpinner); //Create spinner for your reservations
        ArrayAdapter adapter2 = new ArrayAdapter<Varaus>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, resArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2); // Show reservations in spinner

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                } //;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        button.setOnClickListener(new View.OnClickListener() { //Edit reservation when clicked and edit texts filled with new info
            public void onClick(View v) { //Edit item
                Varaus res = (Varaus) spinner2.getSelectedItem(); // Item you want to edit
                resArray.remove(res); //Remove selected item
                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String userName = sharedPref.getString("username","");
                Varaus res1 = new Varaus(spinner.getSelectedItem().toString(),edit2.getText().toString(),edit1.getText().toString(),userName,edit3.getText().toString());
                resArray.add(res1); //Add item with new details
                edit1.getText().clear(); //Clear edit texts
                edit2.getText().clear();
                edit3.getText().clear();
                // Update spinner
                ArrayAdapter adapter2 = new ArrayAdapter<Varaus>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, resArray);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);




            }

        });


    }
}


package com.example.n8154.ht2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;

public class Activity6 extends AppCompatActivity {

    ArrayList<String> resList = new ArrayList<>();
    Spinner spinner;
    Spinner spinner2;
    Varaushallinta gyms;
    ArrayList<String> painisalilist = Dataholder.getInstance().painisalilist;
    ArrayList<String> peilisalilist = Dataholder.getInstance().peilisalilist;
    ArrayList<String> monarilist = Dataholder.getInstance().monarilist;
    ArrayList<String> kuntosalilist = Dataholder.getInstance().kuntosalilist;
    ArrayAdapter adapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        spinner = (Spinner) findViewById(R.id.reservationSpinner);
        spinner2 = (Spinner) findViewById(R.id.gymSpinner);
        button = (Button) findViewById(R.id.button6);
        final XMLwriter write = new XMLwriter();
        //write.writeXml();
        //readXML();
        gyms = new Varaushallinta();
        ArrayList<Sali> arr = gyms.mygyms();

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr); //Create adapter for spinner
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2); //Create spinner to show reservations
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




    button.setOnClickListener(new View.OnClickListener() { //Show reservations on spinner
        public void onClick(View v) { //Edit item

            // Update spinner with selected gyms' reservations
            if (spinner2.getSelectedItem().toString() == "Painisali") {
                ArrayAdapter adapter3 = new ArrayAdapter<String>(Activity6.this, android.R.layout.simple_spinner_dropdown_item, painisalilist);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);
            } else if (spinner2.getSelectedItem().toString() == "Peilisali") {
                ArrayAdapter adapter3 = new ArrayAdapter<String>(Activity6.this, android.R.layout.simple_spinner_dropdown_item, peilisalilist);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);
            } else if (spinner2.getSelectedItem().toString() == "Kuntosali") {
                ArrayAdapter adapter3 = new ArrayAdapter<String>(Activity6.this, android.R.layout.simple_spinner_dropdown_item, kuntosalilist);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);
            } else if (spinner2.getSelectedItem().toString() == "Monitoimisali") {
                ArrayAdapter adapter3 = new ArrayAdapter<String>(Activity6.this, android.R.layout.simple_spinner_dropdown_item, monarilist);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);
            }
        }
    });
}}

package com.example.n8154.ht2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class Activity4 extends AppCompatActivity {
    ArrayList<Varaus> resArray = Dataholder.getInstance().reservationlist;
    TextView text;
    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        button = (Button) findViewById(R.id.removeButton);
        text = (TextView) findViewById(R.id.showDetails);
        spinner = (Spinner) findViewById(R.id.reservationSpinner);  //Create spinner to show your reservations
        ArrayAdapter adapter = new ArrayAdapter<Varaus>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, resArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Show reservations on spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Varaus res = (Varaus) spinner.getSelectedItem(); //Get object to remove
                // Show details when clicked
                text.setText(res.getGymName()+"\n"+res.getDate() + "klo " + res.getTime() + "\n Varaaja: " + res.getName() + "\n" + res.getUse());

                try {
                } catch (Exception e) {
                    e.printStackTrace();
                } //;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //Remove item by clicking
                Varaus res = (Varaus) spinner.getSelectedItem();
                resArray.remove(res); //Remove selected object
                ArrayAdapter adapter = new ArrayAdapter<Varaus>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, resArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter); // Update spinner
                text.setText("");
            }

        });

    }


}

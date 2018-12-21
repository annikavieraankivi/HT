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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Activity3 extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> gymArray = new ArrayList<>();
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

        //final XMLwriter write = new XMLwriter();
        //write.writeXml(); //Write reservations to xml

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        edit1 = (EditText) findViewById(R.id.editDate);
        edit2 = (EditText) findViewById(R.id.editTime);
        edit3 = (EditText) findViewById(R.id.editReason);
        button = findViewById(R.id.saveButton);
        text = (TextView) findViewById(R.id.textView);

        spinner = (Spinner) findViewById(R.id.gymSpinner);
        gyms = new Varaushallinta();
        ArrayList<Sali> arr = gyms.mygyms(); // Get list of gymnames from Varaushallinta
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr); //Create adapter for spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); //Create spinner to show gyms

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


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //Save reservation if gym is free
                SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String gymchoice = spinner.getSelectedItem().toString();
                String datechoice = edit1.getText().toString();
                String timechoice = edit2.getText().toString();
                if (checkIfFree(gymchoice, datechoice, timechoice) == 0) { // Check if gym is free
                    Toast.makeText(getBaseContext(),"Aika on varattu!",Toast.LENGTH_LONG).show();
                    edit1.getText().clear();
                    edit2.getText().clear();
                    edit3.getText().clear();
                } else {
                    String userName = sharedPref.getString("username", "");
                    Varaus res1 = new Varaus(spinner.getSelectedItem().toString(), edit2.getText().toString(), edit1.getText().toString(), userName, edit3.getText().toString());
                    resArray.add(res1);
                    edit1.getText().clear();
                    edit2.getText().clear();
                    edit3.getText().clear();
                }
            }

        });
    }

    public int checkIfFree (String gymchoice, String datechoice, String timechoice) { // Check if gym is free
        try {
            File file = new File("/data/user/0/com.example.n8154.ht2/files/reservations.xml");
            InputStream is = new FileInputStream(file.getPath());
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(is));
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getDocumentElement().getElementsByTagName("reservation"); // Get all reservations
            for (int i=0; i<nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String test1 = element.getElementsByTagName("gymname").item(0).getTextContent();
                    String test2 = element.getElementsByTagName("date").item(0).getTextContent();
                    String test3 = element.getElementsByTagName("time").item(0).getTextContent();
                    System.out.println(gymchoice + datechoice);
                    if (test1.equals(gymchoice) && test2.equals(datechoice) && test3.equals(timechoice)) { // If gym, date and time matches, return 0;

                        return 0;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("#########DONE#########");
        } return 1; // If gym is free, return 1
    }
    }


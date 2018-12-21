package com.example.n8154.ht2;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

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

public class Activity6 extends AppCompatActivity {

    ArrayList<String> resList = new ArrayList<>();
    ArrayList<String> showList = new ArrayList<>();
    Spinner spinner;
    Spinner spinner2;
    Varaushallinta gyms;
    ListView simpleList;
    Button button;
    EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        spinner2 = (Spinner) findViewById(R.id.gymSpinner);
        button = (Button) findViewById(R.id.button6);
        text = (EditText) findViewById(R.id.editDate);
        Context context = Activity6.this;
        final XMLwriter write = new XMLwriter();
        write.writeXml(context);
        readXML(resList);
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


        button.setOnClickListener(new View.OnClickListener() { //Show reservations matching choices on spinner when clicked
            public void onClick(View v) {
                String gymchoice = spinner2.getSelectedItem().toString();
                String datechoice = text.getText().toString();
                ArrayList<String> showChoicedReservations = new ArrayList<>();
                readXMLwithchoices(showChoicedReservations, gymchoice, datechoice); // Call function which shows matching reservations
            }
        });
    }
        public void readXML (ArrayList showArray) { // Read all items from XML to show in listview
            try {
                File file = new File("/data/user/0/com.example.n8154.ht2/files/reservations.xml");
                InputStream is = new FileInputStream(file.getPath());
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.parse(new InputSource(is));
                doc.getDocumentElement().normalize();
                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                showArray.clear();
                NodeList nList = doc.getDocumentElement().getElementsByTagName("reservation"); // Get all reservations
                for (int i=0; i<nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        showArray.add(element.getElementsByTagName("gymname").item(0).getTextContent().toString()+" "+element.getElementsByTagName("date").item(0).getTextContent().toString()+" klo "+element.getElementsByTagName("time").item(0).getTextContent().toString()+" " +element.getElementsByTagName("name").item(0).getTextContent().toString()+", " +element.getElementsByTagName("use").item(0).getTextContent().toString());


                    }
                } // Show in listview
                simpleList = (ListView) findViewById(R.id.simpleListView);
                ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, showArray);
                simpleList.setAdapter(movieAdapter);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } finally {
                System.out.println("#########DONE#########");
            }
        }

    public void readXMLwithchoices (ArrayList showArray, String gymchoice, String datechoice) { // Shows mathing reservations to gym and date choices
        try {
            File file = new File("/data/user/0/com.example.n8154.ht2/files/reservations.xml");
            InputStream is = new FileInputStream(file.getPath());
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(is));
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            showArray.clear();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("reservation");
            for (int i=0; i<nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String test1 = element.getElementsByTagName("gymname").item(0).getTextContent();
                    String test2 = element.getElementsByTagName("date").item(0).getTextContent();
                    if (test1.equals(gymchoice) && test2.equals(datechoice)) {
                        showArray.add(element.getElementsByTagName("gymname").item(0).getTextContent().toString()+" "+element.getElementsByTagName("date").item(0).getTextContent().toString()+" klo "+element.getElementsByTagName("time").item(0).getTextContent().toString()+" " +element.getElementsByTagName("name").item(0).getTextContent().toString()+", " +element.getElementsByTagName("use").item(0).getTextContent().toString());
                    }


                }
            } // Show matching reservations in listview
            simpleList = (ListView) findViewById(R.id.simpleListView);
            ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, showArray);
            simpleList.setAdapter(movieAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("#########DONE#########");
        }
    }
}

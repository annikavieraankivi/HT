package com.example.n8154.ht2;


import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;
import java.io.OutputStreamWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class XMLwriter extends AppCompatActivity{

    Context context = null;

    public void writeXml(){
        context = XMLwriter.this;
        ArrayList<Varaus> reservations = new ArrayList<>();
        reservations.add(new Varaus("Monitoimisali", "11", "25.12.", "Liisa", "Tanssitunti"));
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "Reservations");
            serializer.attribute("", "number", String.valueOf(reservations.size()));
            for (Varaus res: reservations) {
                serializer.startTag("", "reservation");
                serializer.attribute("", "use", res.getUse());

                serializer.startTag("", "name");
                serializer.text(res.getName());
                serializer.endTag("", "name");

                serializer.startTag("", "gymname");
                serializer.text(res.getGymName());
                serializer.endTag("", "gymname");

                serializer.startTag("", "time");
                serializer.text(res.getTime());
                serializer.endTag("", "time");

                serializer.startTag("", "date");
                serializer.text(res.getDate());
                serializer.endTag("", "date");

                serializer.endTag("", "reservation");
            }
            serializer.endTag("", "Reservations");
            serializer.endDocument();
            String result = writer.toString();

            OutputStreamWriter fos = new OutputStreamWriter(context.openFileOutput("reservations.xml", context.MODE_PRIVATE));
            //fos.write(result);
            //fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}}


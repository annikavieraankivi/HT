package com.example.n8154.ht2;

import java.util.ArrayList;


public class Varaushallinta { // Stores list of gyms
    private ArrayList<Sali> listofgyms;

    public ArrayList<Sali> mygyms() {
        ArrayList<Sali> listofgyms = new ArrayList<>();
        listofgyms.add(new Sali("Painisali"));
        listofgyms.add(new Sali("Kuntosali"));
        listofgyms.add(new Sali("Peilisali"));
        listofgyms.add(new Sali("Monitoimisali"));
        return(listofgyms);
    }
}

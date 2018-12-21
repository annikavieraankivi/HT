package com.example.n8154.ht2;

import java.util.ArrayList;

public class Dataholder {
    final ArrayList<Varaus> reservationlist = new ArrayList<>();
    final ArrayList<String> painisalilist = new ArrayList<>();
    final ArrayList<String> peilisalilist = new ArrayList<>();
    final ArrayList<String> monarilist = new ArrayList<>();
    final ArrayList<String> kuntosalilist = new ArrayList<>();

    private Dataholder() {}

    static Dataholder getInstance() {
        if (instance == null) {
            instance = new Dataholder();

        }
        return instance;
    }
    private static Dataholder instance;


}

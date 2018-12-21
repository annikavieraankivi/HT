package com.example.n8154.ht2;

import java.util.ArrayList;

public class Dataholder { //Stores all reservations (could be included in Sali.class)
    final ArrayList<Varaus> reservationlist = new ArrayList<>();

    private Dataholder() {}

    static Dataholder getInstance() {
        if (instance == null) {
            instance = new Dataholder();

        }
        return instance;
    }
    private static Dataholder instance;


}

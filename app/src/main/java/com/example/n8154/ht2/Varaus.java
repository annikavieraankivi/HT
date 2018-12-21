package com.example.n8154.ht2;

import com.google.gson.annotations.SerializedName;

public class Varaus { // Stores reservation info
    private String gymName;
    private String time;
    private String date;
    private String name;
    private String use;

    public Varaus(String g, String t, String d, String n, String u) {
        gymName = g;
        time = t;
        date = d;
        name = n;
        use = u;
    }

    public void setGymName(String g) {
        gymName = g;
    }

    public void setTime(String t) {
        time = t;
    }

    public void setDate(String d) {
        date = d;
    }

    public void setName(String n) {
        name = n;
    }

    public void setUse(String u) {
        use = u;
    }

    public String getGymName() {
        return gymName;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getUse() {
        return use;
    }

    public String toString() {
        return ("Sali: " + gymName + "\n"+  date + "klo " + time + "\nVaraaja: " + name + "\n" + use);
    }
}

package com.stressmanager;


public class StressEvent {

    //Calendar Id
    int calId;
    //stress level
    int stresslvl;

    //data that Google gives each event
    String deets;

    //constructors
    StressEvent(int calID, int stress, String deets) {
        calId = calID;
        stresslvl = stress;
        this.deets = deets;
    }
    //getters
    public int getCalId() {
        return this.calId;
    }
    public int getStressLvl() {
        return this.stresslvl;
    }
    public String getDeets() {
        return this.deets;
    }
    //Setters
    public void setCalId(int id) {
        this.calId = id;
    }
    public void setStressLvl(int lvl) {
        this.stresslvl = lvl;
    }
    public void setDeets(String deet) {
        this.deets = deet;
    }
}

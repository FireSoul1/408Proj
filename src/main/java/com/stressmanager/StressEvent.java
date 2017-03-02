package com.stressmanager;


public class StressEvent {

    //Calendar Id
    int calId;
    //stress level
    int stresslvl;

    //constructors
    StressEvent(int calID, int stress) {
        calId = calID;
        stresslvl = stress;
    }
    //getters
    public int getCalId() {
        return this.calId;
    }
    public int getStressLvl() {
        return this.stresslvl;
    }
    //Setters
    public void setCalId(int id) {
        this.calId = id;
    }
    public void setStressLvl(int lvl) {
        this.stresslvl = lvl;
    }
}

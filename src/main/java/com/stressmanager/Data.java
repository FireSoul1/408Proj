package com.stressmanager;
/*
*
*This is can become a Holder Class for an Kind of Data
*/
import java.util.*;

public class Data {

    //An array of stress relief advice
    static String[] advice = {
    "Treat yourself to something today. You have had a hard week.",//SAT
    "Don't give up! You are almost there!!",//WEDNES
    "Don't let the week get you down. There is always the weekend!",//TUES
    "IT IS FRIDAY, OMG!",//FRI
    "Go for a walk. It is really nice outside",//SUN
    "Well, time to go to the Cactus",//THURS
    "Be the best you, you can be",//MON
    };
    public static String getSomeWisdom(){
         Calendar rightNow = Calendar.getInstance();
         int day = rightNow.get(Calendar.DAY_OF_WEEK);
         //RETURN ADVICE based on the day
         switch(day) {
             case Calendar.MONDAY: return advice[6];
             case Calendar.TUESDAY: return advice[2];
             case Calendar.WEDNESDAY: return advice[1];
             case Calendar.THURSDAY: return advice[5];
             case Calendar.FRIDAY: return advice[3];
             case Calendar.SATURDAY: return advice[0];
             case Calendar.SUNDAY: return advice[4];
             default: return "There is nothing I can say that can make things better";
         }


    }


    String advise;
    Data(String html) {
        this.advise = html;
    }
    public void setAdvice(String in) {
        this.advise = in;
    }

    public String getAdvice() {
        return this.advise;
    }
}

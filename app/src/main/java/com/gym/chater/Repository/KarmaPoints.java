package com.gym.chater.Repository;

public class KarmaPoints {
    static private int karmaPoints;

    static {
        karmaPoints = 0;
    }

    static public int getkarmaPoints(){
        return karmaPoints;
    }
    static public void setkarmaPoints(int karmaPoints){
        KarmaPoints.karmaPoints = karmaPoints;
    }
    static public void addkarmaPoints(int karmaPoints){
        KarmaPoints.karmaPoints += karmaPoints;
    }
}

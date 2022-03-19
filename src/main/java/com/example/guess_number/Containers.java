package com.example.guess_number;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
/*
Klasa przeznaczona do stworzenia dwóch kontenerów(pojemników) danych(mapy i listy) oraz metod obsługujących te pojemniki
 */

public class Containers {
    static final Map<Integer, Sample> samples_set = new HashMap<>();
    static ArrayList<Sample> highscores = new ArrayList<Sample>();

    static Sample getSample(int id){
        return samples_set.get(id);
    }
    static int getSampleId(int id){
        return samples_set.get(id).getId();
    }
    static int getSampleNumber(int id){
        return samples_set.get(id).getNumber();
    }
    static int getSampleAttempt(int id){
        return samples_set.get(id).getAttempt();
    }
    static boolean getSampleWinner(int id){
        return samples_set.get(id).getWinner();
    }
    static void setSampleAttempt(int id){
        samples_set.get(id).setAttempt();
    }
    static void setSampleWinner(int id){
        samples_set.get(id).setWinner();
    }
    static void startSampleTime(int id){
        samples_set.get(id).startTime();
    }
    static void stopSampleTime(int id){
        samples_set.get(id).stopTime();
    }
    static void sortHighscores(){
        Collections.sort(highscores);
    }
    static void addHighscores(int id){
        if (highscores.size()<10) {
            highscores.add(samples_set.get(id));
        }
        else{
            if(highscores.get(9).getAttempt()>samples_set.get(id).getAttempt()){
                highscores.set(9,samples_set.get(id));
            }
        }
    }
    static String printHighscores(){
        String hs = "";
        int nr = 1;
        for(Sample s : highscores){
            hs+=nr+"|Id: "+s.getId()+"|Attempts: "+s.getAttempt()+"|Time: "+s.getTime()+"<br>";
            nr++;
        }
        return hs;
    }
}

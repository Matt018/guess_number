package com.example.guess_number;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.util.Random;

@Service
class Sample implements Comparable<Sample> {
    private static final Random rand = new Random();
    private int id;
    private int number;
    private int attempt = 0;
    private boolean winner = false;
    private StopWatch time;
    private boolean timeStarted = false;

    public Sample() {
        id = rand.nextInt(10000)+1;
        number = rand.nextInt(100)+1;
        time = new StopWatch();
    }
        public int getId() {
        return id;
        }
        public int getAttempt(){
        return attempt;
        }
        public int getNumber() {
        return number;
    }
        public boolean getWinner(){
        return winner;
        }
        public double getTime(){
        return time.getTotalTimeSeconds();
        }
        public void setAttempt(){
        attempt++;
    }
        public void setWinner(){
        winner = true;
        }
        public void startTime() {
            if (!timeStarted) {
                time.start();
                timeStarted=true;
            }
        }

        public void stopTime(){
        time.stop();
        }
        @Override
        public int compareTo(Sample sample){
        return (this.attempt-sample.attempt);
        }

}

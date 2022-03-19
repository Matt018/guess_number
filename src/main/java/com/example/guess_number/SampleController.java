package com.example.guess_number;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StopWatch;

@RestController
public class SampleController {

    @GetMapping("apigame/start")
    public static int start(){
        Sample sample = new Sample();
        Containers.samples_set.put(sample.getId(), sample);
        return sample.getId();
    }
    @GetMapping("apigame/guess")
    public static String checkshot(@RequestParam int id, int shot){
        if (Containers.getSample(id)==null){
            return "Wrong Id";
        }
        if(Containers.getSampleWinner(id)){
            return "already guessed";
        }
        Containers.startSampleTime(id);
        if(shot<Containers.getSampleNumber(id)){
            Containers.setSampleAttempt(id);
            return "Id: "+Containers.getSampleId(id)+"Attempt: "+Containers.getSampleAttempt(id)+"too small";
        }
        else if(shot>Containers.getSampleNumber(id)){
            Containers.setSampleAttempt(id);
            return "Id: "+Containers.getSampleId(id)+"Attempt: "+Containers.getSampleAttempt(id)+"too big";
        }
        else{
            Containers.stopSampleTime(id);
            Containers.setSampleAttempt(id);
            Containers.setSampleWinner(id);
            Containers.addHighscores(id);
            Containers.sortHighscores();
            return "Id: "+Containers.getSampleId(id)+"Attempt: "+Containers.getSampleAttempt(id)+"WINNER!!";
        }
    }
    @GetMapping("apigame/highscores")
    public static String highscores(){
        return Containers.printHighscores();
    }

}

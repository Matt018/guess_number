package com.example.guess_number;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/*
Klasa testująca metody obsługujące listę i mapę z klasy Containers.
 */

@SpringBootTest
public class ContainerClassTest {

    Sample test_sample = new Sample();
    int id = test_sample.getId();
    @BeforeEach
    public void setUp(){
        Containers.samples_set.put(id, test_sample);
    }
    /*
    test metody zwracającej instancję Sample znajdującą się w mapie przy podaniu prawidłowego id
     */
    @Test
    public void getSampleTest(){
        assertEquals(test_sample,Containers.getSample(id));
        assertNull(Containers.getSample(id+1));
        assertNull(Containers.getSample(id-1));
    }
    /*
    Test wszystkich metod zapewniających dostęp do pól instancji Sample znajdującej się w mapie
     */
    @Test
    public void getSampleFieldsTest(){
        assertAll("Access methods to class Sample fields",
                ()->assertEquals(test_sample.getId(),Containers.getSampleId(id)),
                ()->assertEquals(test_sample.getNumber(),Containers.getSampleNumber(id)),
                ()->assertEquals(test_sample.getAttempt(),Containers.getSampleAttempt(id)),
                ()->assertEquals(test_sample.getWinner(),Containers.getSampleWinner(id))
        );
    }
    /*
    test metody zwiększjącej liczbę prób elementu mapy(instancji Sample)
     */
    @Test
    public void setSampleAttemptTest(){
        for(int i = 0;i<10;i++){
            Containers.setSampleAttempt(id);
        }
        assertEquals(10,test_sample.getAttempt());
    }
    /*
    test metody zmieniające pole winner elementu mapy(instancji Sample)
     */
    @Test
    public void setSampleWinnerTest(){
        Containers.setSampleWinner(id);
        assertTrue(test_sample.getWinner());
    }
    /*
    test sprawdzający poprawność obliczania czasu (działanie metod startSampleTime i stopSampleTime)
    dla konkretnego elementu mapy
     */
    @Test
    public void  SampleTimeMeasurementTest() throws InterruptedException {
        assertEquals(0,test_sample.getTime());
        Containers.startSampleTime(id);
        Thread.sleep(2000);
        Containers.stopSampleTime(id);
        assertEquals(2,Math.round(test_sample.getTime()));
    }
    //test działania metody sortującej listę highscores wg. ilości prób(attemp)
    @Test
    public void sortHighscoresTest(){
        Sample [] order = new Sample[4];//tablica pomocnicza ukazująca w jakiej kolejności pierwotnie znajdowały się elementy(sample)
        for(int i =0;i<4;i++){
            Containers.highscores.add(new Sample());
            order[i]=Containers.highscores.get(i);
        }
        for(int i = 0; i<4;i++){
            Containers.highscores.get(0).setAttempt();
        }
        for(int i = 0; i<2;i++){
            Containers.highscores.get(2).setAttempt();
        }
        Containers.highscores.get(3).setAttempt();
        Containers.sortHighscores();
        /*
        sprawdzenie czy po wywołaniu metody sortHighscores elementy listy ustawią się w odpowiedniej kolejności
         */
        assertAll("Checking order",
                ()->assertEquals(order[1],Containers.highscores.get(0)),
                ()->assertEquals(order[3],Containers.highscores.get(1)),
                ()->assertEquals(order[2],Containers.highscores.get(2)),
                ()->assertEquals(order[0],Containers.highscores.get(3))
                );
        Containers.highscores.clear();
    }
    /*
    sprawdzenie poprawności działania metody addHighscores. Utworzenie listy z 9 elementami i sprawdzenie czy można dodać 10.
    Następnie próba dodania 11 elementu posiadającego taką samą wartość pola attempt.
    Zmiana wartości pola attempt ostatniego elementu z listy i próba ponownego dodania elementu
     */
    @Test
    public void addHighScoresTest(){
        for(int i =0;i<9;i++){
            Containers.highscores.add(new Sample());
        }
        Containers.addHighscores(id);

        assertEquals(test_sample,Containers.highscores.get(9));

        Sample test_sample2 = new Sample();
        int id2 = test_sample2.getId();
        Containers.samples_set.put(id2, test_sample2);
        Containers.addHighscores(id2);

        assertNotEquals(test_sample2,Containers.highscores.get(9));

        Containers.highscores.get(9).setAttempt();
        Containers.addHighscores(id2);

        assertEquals(test_sample2,Containers.highscores.get(9));
        Containers.highscores.clear();
    }
    /*
    sprawdzenie kompletności i poprawności łańcuch zwracanego przez metodę printHightscores
     */
    @Test
    public void printHighscoresTest(){
        for(int i =0;i<3;i++){
            Containers.highscores.add(new Sample());
        }
        assertEquals(
                1+"|Id: "+Containers.highscores.get(0).getId()+"|Attempts: "+Containers.highscores.get(0).getAttempt()+"|Time: "+Containers.highscores.get(0).getTime()+"<br>"
                +2+"|Id: "+Containers.highscores.get(1).getId()+"|Attempts: "+Containers.highscores.get(1).getAttempt()+"|Time: "+Containers.highscores.get(1).getTime()+"<br>"
                +3+"|Id: "+Containers.highscores.get(2).getId()+"|Attempts: "+Containers.highscores.get(2).getAttempt()+"|Time: "+Containers.highscores.get(2).getTime()+"<br>",
                Containers.printHighscores());
        Containers.highscores.clear();
    }
}

package com.example.guess_number;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.util.Random;
/*
Klasa testowa testująca podstawową klasę Sample: tworzenie instancji, dostęp do pól klasy, możliwość zmiany pól klasy.
Testy metod wywoływanych w klasie SampleController.
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GuessNumberApplicationTests {

    private static Sample sample;
    private static Random rand = new Random();

    @Test
    @BeforeAll
    public static void canIstantiateSample(){
        sample = new Sample();
        assertNotNull(sample);
    }
    @Test
    public void isGetIdReturningCorrectId(){
        assertTrue(sample.getId()>0&&sample.getId()<10001);
    }
    @Test
    @Order(1)
    public void isAttemptInitializedWith0(){
        assertEquals(0, sample.getAttempt());
    }
    @Test
    public void isNumberInTheRange1_100(){
        assertTrue(sample.getNumber()>0 && sample.getNumber()<101);
    }
    @Test
    @Order(2)
    public void isWinnerInitializedWithFalse() {
        assertFalse(sample.getWinner());
    }
    @Test
    public void isSetAttemptIncreasingAttemptBy1(){
        sample.setAttempt();
        assertEquals(1,sample.getAttempt());
        for (int i =0;i<3;i++){
            sample.setAttempt();
        }
        assertEquals(4,sample.getAttempt());
    }
    @Test
    public void isSetWinnerSettingTrue(){
        sample.setWinner();
        assertTrue(sample.getWinner());
    }
    /*
    Test metody start w SampleController sprawdzający czy metoda zwraca id i pod tym id umieszcza instancję Sample w mapie
     */
    @Test
    public void isStartWorkingCorrect(){
        int id = SampleController.start();
        assertEquals(id,Containers.samples_set.get(id).getId());
    }
    /*
    seria testów sprawdzająca poprawność działania metody checkshot w SampleController
     */
    @Test
    public void isCheckShotWorkingCorrect(){
        int id = SampleController.start();
        int shot;
        assertEquals("Wrong Id",SampleController.checkshot(id+1,50)); //przypadek podania nieistniejącego id

        shot = Containers.getSampleNumber(id)-1;
        assertEquals("Id: "+Containers.getSampleId(id)+"Attempt: "+(Containers.getSampleAttempt(id)+1) //przypadek podnia liczby mniejszej od wylosowanej
                +"too small", SampleController.checkshot(id, shot));

        shot = Containers.getSampleNumber(id)+1;
        assertEquals("Id: "+Containers.getSampleId(id)+"Attempt: "+(Containers.getSampleAttempt(id)+1) //przypadek podania liczby większej od wylosowanej
                +"too big", SampleController.checkshot(id, shot));

        shot = Containers.getSampleNumber(id);
        assertEquals("Id: "+Containers.getSampleId(id)+"Attempt: "+(Containers.getSampleAttempt(id)+1) //zgadnięcie liczby
                +"WINNER!!", SampleController.checkshot(id, shot));
        assertEquals("already guessed",SampleController.checkshot(id, shot)); //próba ponownego odgadnięcia już zakończonej sesji

    }
}

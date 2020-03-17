package com.algoritmos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void traduceWomanTest() {
        Controller controller = new Controller();
        controller.fillDictionary("dic.txt");
        assertEquals("mujer mujer ", controller.traduce("Woman woman"));
    }
    @Test
    void traduceSentenceTest() {
        Controller controller = new Controller();
        controller.fillDictionary("dic.txt");
        assertEquals("casa mujer *Toast* ", controller.traduce("house Woman Toast"));
    }

    @Test
    void traduceWorngSentenceTest() {
        Controller controller = new Controller();
        controller.fillDictionary("dic.txt");
        assertEquals("*casa* *mujer* *Toast* ", controller.traduce("casa mujer Toast"));
    }
}
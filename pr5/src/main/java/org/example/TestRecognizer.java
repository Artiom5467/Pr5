package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecognizer {

    public enum State {
        S, // start
        STATE1, // 'T'
        STATE2, // 'TE'
        STATE3, // 'TES'
        F // 'TEST'
    }

    public static State processString(String input) {
        State currentState = State.S;

        for (char c : input.toCharArray()) {
            currentState = (currentState == State.S && c == 'T') ? State.STATE1 :
                    (currentState == State.STATE1 && c == 'E') ? State.STATE2 :
                            (currentState == State.STATE1 && c == 'T') ? State.STATE1 :
                                    (currentState == State.STATE1) ? State.S :
                                            (currentState == State.STATE2 && c == 'S') ? State.STATE3 :
                                                    (currentState == State.STATE2 && c == 'T') ? State.STATE1 :
                                                            (currentState == State.STATE2) ? State.S :
                                                                    (currentState == State.STATE3 && c == 'T') ? State.F :
                                                                            (currentState == State.STATE3) ? State.S :
                                                                                    currentState;
        }

        return currentState;
    }

    public static void main(String[] args) {
        System.out.println(processString("abcTESTabc")); // Should print: F
        System.out.println(processString("abcTES"));     // Should print: STATE3
    }
}

package com.tau.commstudy.entities;

public class TestQuestion {

    private long id;
    private long testId;
    private char numberInTest; //small number
    
    public TestQuestion() {
		
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public char getNumberInTest() {
        return numberInTest;
    }

    public void setNumberInTest(char numberInTest) {
        this.numberInTest = numberInTest;
    }

}

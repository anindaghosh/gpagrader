package com.khakidonald.srmgrader;

public class Semester {
    private String sem;
    private float gpa;
    private int credits;

    public Semester(String sem){
        this.sem = sem;
        this.gpa = 0;
        this.credits = 0;
    }

    public void setGpa(float gpa){
        this.gpa = gpa;
    }

    public void setCredits(int credits){
        this.credits = credits;
    }

    public String getSem() {
        return sem;
    }

    public float getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }
}


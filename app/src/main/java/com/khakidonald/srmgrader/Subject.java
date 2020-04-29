package com.khakidonald.srmgrader;

public class Subject {
    private boolean isSelected = false;
    private String credits, grade;
    private int spinnerCredPos, spinnerGradPos;

    public Subject() {
        this.credits = "Select Credits";
        this.grade = "Select Grade";

    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCredits() {
        return credits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public int getSpinnerCredPos() {
        return spinnerCredPos;
    }

    public void setSpinnerCredPos(int position) {
        this.spinnerCredPos = position;
    }

    public int getSpinnerGradPos() {
        return spinnerGradPos;
    }

    public void setSpinnerGradPos(int position) {
        this.spinnerGradPos = position;
    }
}

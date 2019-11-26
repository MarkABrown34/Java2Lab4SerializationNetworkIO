package edu.cscc;

public class Surname {
    public String surname;
    public int rank;
    public double frequency;

    Surname() {
        surname = null;
        rank = -1;
        frequency = -1;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void printRecord() {
        System.out.print("Surname: " + this.surname);
        System.out.print(" frequency: " + this.frequency + "%");
        System.out.println(" rank: " + this.rank);
    }
}

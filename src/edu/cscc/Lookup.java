package edu.cscc;

import java.util.HashMap;
import java.util.Scanner;

public class Lookup {

    private static Scanner input = new Scanner(System.in);
    public static HashMap<String, Surname> thisMap = new CensusData().create();

    public static void main(String[] args) {
        System.out.println("Surname Frequency Lookup Utility");
        String freshOrSaved = "";
        while (freshOrSaved.isEmpty()) {
            System.out.println("Would you like to download fresh data or use previously saved data? fresh/saved");
            freshOrSaved = input.next();
            if (freshOrSaved.equalsIgnoreCase("fresh")) {
                thisMap = new CensusData().create();
                System.out.println("Fresh data has been downloaded and saved to surname.dat for future use.");
            } else if (freshOrSaved.equalsIgnoreCase("saved")) {
                thisMap = new CensusData().readDataFile();
                System.out.println("Loaded saved data.");
            } else {
                freshOrSaved = "";
                System.out.println("Please type \"fresh\" or \"saved\".");
            }
        }

        String searchName = "";
        while (!searchName.equals("quit")) {
            System.out.println("Enter a surname (or quit to end): ");
            searchName = input.next().toUpperCase();
            if (searchName.equalsIgnoreCase("quit")) {
                break;
            } else if (thisMap.containsKey(searchName)) {
                Surname surname = thisMap.get(searchName);
                System.out.println("Surname: " + surname.getSurname().toUpperCase() + " Frequency: " + surname.getFrequency() + " Rank: " + surname.getRank());
            } else {
                System.out.println("Surname: " + searchName.toUpperCase() + " not found");
            }
        }
    }

}
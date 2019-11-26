package edu.cscc;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class CensusData {
    private HashMap<String, Surname> censusData = new HashMap<>();

    public HashMap<String, Surname> create() {
        try {
            URL url = new URL("https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                try {
                    String name = s.substring(0, 13).trim();
                    String[] strArray = {s.substring(15, 20).trim(), s.substring(29, 34).trim()};
                    Surname surname = new Surname();
                    surname.setSurname(name);
                    surname.setFrequency(Double.parseDouble(strArray[0]));
                    surname.setRank(Integer.parseInt(strArray[1]));
                    censusData.put(name, surname);
                } catch (Exception d) {
                    d.printStackTrace();
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
        }
        try {
            writeDataFile(censusData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusData;
    }

    private void writeDataFile(HashMap<String, Surname> aMap) throws IOException {
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("./surname.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(aMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public HashMap<String, Surname> readDataFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./surname.dat"));
            censusData = (HashMap<String, Surname>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return censusData;
    }
}

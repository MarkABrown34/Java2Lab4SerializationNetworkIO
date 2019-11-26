package edu.cscc;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class CensusData {
    private HashMap<String, String[]> censusdata = new HashMap<>();

    public HashMap<String, String[]> create() {
        //LinkedHashMap<String, String> themap = new LinkedHashMap<>();
        try {
            URL url = new URL("https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader brdr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s = null;
            while ((s = brdr.readLine()) != null) {
                //System.out.println(s);
                try {
                    //DELSOL         0.000 84.903  44088
                    String name = s.substring(0, 13).trim();
                    //String[] strArray = new String[2];
                    //String strArray[0] = s.substring(15, 20).trim();
                    //String strArray[1] = s.substring(29, 34);
                    String[] strArray = {s.substring(15, 20).trim(),s.substring(29, 34)};
                    System.out.println("Name: "+name+"|");
                    System.out.println("Frequency: |"+strArray[0]+"|");
                    System.out.println("Rank: |"+strArray[1]+"|");
                    censusdata.put(name, strArray);
                } catch (Exception d) {
                    d.printStackTrace();
                }
            }
            brdr.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        try {
            writeDataFile(censusdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusdata;
    }

    private void writeDataFile(HashMap<String, String[]> amap) throws IOException {
        try
        {
            FileOutputStream fos =
                    new FileOutputStream("./surname.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(amap);
            oos.close();
            fos.close();
            //System.out.println("Serialized HashMap data is saved in ./surname.dat");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }


    public HashMap<String, String[]> readDataFile()  {
        //LinkedHashMap<String, String> map = null;
        try
        {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./surname.dat"));
            censusdata = (HashMap<String, String[]>) ois.readObject();
            ois.close();

        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();

        }

        return censusdata;
    }

    //public boolean exists(String surnamesearch) { return !censusdata.get(surnamesearch).();}

    public void search (String surnamesearch) {
        if (exists(surnamesearch)) {
            System.out.println("found it");
        }
        else {
            //respond that the name doesn't exist
            System.out.println("nope");
        }
    }

    public boolean exists (String surnamesearch) {
        return true;
    }

}

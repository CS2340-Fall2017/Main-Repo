package edu.gatech.m4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class CSVFile {
    private final InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public HashMap<String, String[]> read(){
        ArrayList<String[]> resultList = new ArrayList<>();
        HashMap<String, String[]> data = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                //edit the date column
                String date = row[1];
                if (date.equals("Created Date")) {

                } else {
                    String[] split = date.split("\\s+");
                    date = split[0];
                    String[] dateSplit = date.split("/");

                    if (dateSplit[0].length() == 1) {
                        dateSplit[0] = "0" + dateSplit[0];
                    }

                    if (dateSplit[1].length() == 1) {
                        dateSplit[1] = "0" + dateSplit[1];
                    }

                    if (dateSplit[2].length() == 2) {
                        dateSplit[2] = "20" + dateSplit[2];
                    }

                    String newDate = dateSplit[2] + "-" + dateSplit[0] + "-" + dateSplit[1];
                    row[1] = newDate;
                }
                data.put(row[0], Arrays.copyOfRange(row, 0, row.length));
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }

        try {
            inputStream.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Error while closing input stream: "+e);
        }

        return data;
    }
}

package edu.gatech.m4;

/**
 * Created by Cesar Porcayo on 10/10/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class CSVFile {
    InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public HashMap<String, String[]> read(){
        ArrayList<String[]> resultList = new ArrayList<String[]>();
        HashMap<String, String[]> data = new HashMap<String, String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                //edit the date column
                String date = row[1];
                if (date.equals("Created Date")) {

                } else {
                    String[] splitted = date.split("\\s+");
                    date = splitted[0];
                    String[] dateSplitted = date.split("/");

                    if (dateSplitted[0].length() == 1) {
                        dateSplitted[0] = "0" + dateSplitted[0];
                    }

                    if (dateSplitted[1].length() == 1) {
                        dateSplitted[1] = "0" + dateSplitted[1];
                    }

                    if (dateSplitted[2].length() == 2) {
                        dateSplitted[2] = "20" + dateSplitted[2];
                    }

                    String newDate = dateSplitted[2] + "-" + dateSplitted[0] + "-" + dateSplitted[1];
                    row[1] = newDate;
                }
                data.put(row[0], Arrays.copyOfRange(row, 0, row.length));
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return data;
    }
}

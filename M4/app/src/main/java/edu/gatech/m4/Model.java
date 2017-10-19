package edu.gatech.m4;

/**
 * Created by Cesar Porcayo on 10/10/2017.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Model {
//    InputStream inputStream = getResources().openRawResource(R.raw.stats);
//    CSVFile csvFile = new CSVFile(inputStream);
//    List scoreList = csvFile.read();

    private static Model INSTANCE = new Model();
    public static Model getInstance() {
        return INSTANCE;
    }
    private HashMap<String, String[]> ratData = new HashMap<String, String[]>();
    public void readCSV(CSVFile file) {
        ratData = file.read();
    }
    public HashMap<String, String[]> getRatData() {
        return ratData;
    }

    public void addReport(String key, String[] reportData) {
        ratData.put(key, reportData);
    }

}

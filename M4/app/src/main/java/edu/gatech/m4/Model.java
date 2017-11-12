package edu.gatech.m4;

/*
  Created by Cesar Porcayo on 10/10/2017.
 */
import java.util.HashMap;

public class Model {
//    InputStream inputStream = getResources().openRawResource(R.raw.stats);
//    CSVFile csvFile = new CSVFile(inputStream);
//    List scoreList = csvFile.read();

    private static final Model INSTANCE = new Model();
    public static Model getInstance() {
        return INSTANCE;
    }
    private HashMap<String, String[]> ratData = new HashMap<>();
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

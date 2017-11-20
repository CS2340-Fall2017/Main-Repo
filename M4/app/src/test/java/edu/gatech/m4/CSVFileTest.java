package edu.gatech.m4;

import android.content.Context;
import android.test.ActivityTestCase;

import org.junit.Test;
import java.io.InputStream;
import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * Created by eduardovargas on 11/19/17.
 */
public class CSVFileTest extends ActivityTestCase{
    /**
     * This Test will check the CSV --> HashMap function. It will test the 4 main components
     * that are constantly used throughout the application, these being: ID, Date, City, Latitude
     * and Longitude
     */
    @Test
    public void testRead() throws Exception {
        Context testContext = getInstrumentation().getTargetContext();
        InputStream inputStream = testContext.getResources().openRawResource(R.raw.load_data_test);
        CSVFile csvFile = new CSVFile(inputStream);
        HashMap<String, String[]> data = csvFile.read();

        String[] ids = {"31464015", "31464024", "31464025", "31464026"};
        String[] cities = {"NEW YORK", "STATEN ISLAND", "STATEN ISLAND", "BROOKLYN"};
        String[] latitudes = {"40.70777155", "40.57520924", "40.63123555", "40.70898692"};
        String[] longitudes = {"-74.0129631", "-74.10454652", "-74.1268776", "-73.9412069" };


        int i = 0;
        for (String key : data.keySet()) {
            String [] tempValues = data.get(key);
            String date = tempValues[0];
            String[] dateVals = date.split("-");
            for (String temp : dateVals) {
                assertEquals(2, temp.length());
            }
            assertEquals(ids[i], key);
            assertEquals(cities[i], tempValues[15]);
            assertEquals(latitudes[i], tempValues[48]);
            assertEquals(longitudes[i], tempValues[49]);
            i++;
        }
    }
}
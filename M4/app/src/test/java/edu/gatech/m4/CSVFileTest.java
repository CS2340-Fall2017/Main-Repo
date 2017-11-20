package edu.gatech.m4;

import android.content.Context;
import android.test.ActivityTestCase;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import java.io.InputStream;
import java.util.HashMap;


import static org.junit.Assert.*;

/**
 * Created by eduardovargas on 11/19/17.
 */
public class CSVFileTest extends ActivityTestCase{
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
        System.out.println("hola");
        for (String key : data.keySet()) {
            System.out.println("Here");
            String [] tempValues = data.get(key);
            assertEquals(ids[i], key);
            assertEquals(cities[i], tempValues[15]);
            assertEquals(latitudes[i], tempValues[48]);
            assertEquals(longitudes[i], tempValues[49]);
            i++;
        }
    }
}
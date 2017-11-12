package edu.gatech.m4;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.UiSettings;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<String> idList;
    private ArrayList<Double> latitudeList;
    private ArrayList<Double> longitudeList;
    private ArrayList<String> boroughList;
    private int numInstancesToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        DBHelper dbHelper = new DBHelper(this);
        idList = new ArrayList<>();
        latitudeList = new ArrayList<>();
        longitudeList = new ArrayList<>();
        boroughList = new ArrayList<>();

        String[] dates = (String[]) getIntent().getSerializableExtra("String");
        numInstancesToShow = Integer.parseInt(dates[2]);
        Log.d("message", dates[0]);
        Cursor cursor = dbHelper.getDateRange(dates[0], dates[1]);
        cursor.moveToFirst();
        for (int i=0; i<numInstancesToShow; i++) {
            if (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_NAME));
                idList.add(id);
                double latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_LATITUDE)));
                latitudeList.add(latitude);
                double longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_LONGITUDE)));
                longitudeList.add(longitude);
                String borough = cursor.getString(cursor.getColumnIndex(DBHelper.REPORT_COLUMN_BOROUGH));
                boroughList.add(borough);
            }
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        for (int a=0; a<numInstancesToShow; a++) {
            LatLng marker = new LatLng(latitudeList.get(a), longitudeList.get(a));
            mMap.addMarker(new MarkerOptions().position(marker).title(idList.get(a)).snippet(boroughList.get(a)));
        }
        
    }
}

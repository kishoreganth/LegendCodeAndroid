package com.example.kisho.aaaasasasa;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.kisho.aaaasasasa.R.id.toolbar;


public class LocEntryActivity extends AppCompatActivity {

    GPSTracker gps;

    SearchView search;
    Toolbar toolbar;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;


    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_entry);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_delete);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocEntryActivity.this,MainActivity.class));
            }
        });


        search=(SearchView) findViewById(R.id.searchView1);
        search.setQueryHint("Search");

        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                //	Toast.makeText(getBaseContext(), newText,
                return false;
            }
        });



    }

    public void currentLocation(View view){




        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                //execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create class object
        gps = new GPSTracker(LocEntryActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Geocoder geocoder = new Geocoder(LocEntryActivity.this, Locale.getDefault());
            List<Address> addresses ;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 3);
                Name = addresses.get(0).getAddressLine(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                    + latitude + "\nLong: " + longitude + "\n" + Name
                    , Toast.LENGTH_LONG).show();

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }



        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("CURRENT_LOCATION",Name);
        startActivity(i);

    }

}

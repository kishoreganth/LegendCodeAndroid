package com.example.kisho.aaaasasasa;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView currentLocation;

Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentLocation = (TextView)findViewById(R.id.texty2);

        String currentLoc = getIntent().getStringExtra("CURRENT_LOCATION");
        currentLocation.setText(currentLoc);

    }



    public void setLoc(View view){
        startActivity(new Intent(this,LocEntryActivity.class));
    }
}
package com.example.ej05_juan_buades;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class TitlePlaceActivity extends FragmentActivity {

    GoogleMap mMap;
    String selectedLocation, selectedHour, selectedMinute, selectedDate, selectedTitle;
    EditText et_title, et_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        et_title = findViewById(R.id.etDateTitle);
        et_location = findViewById(R.id.etLocation);
    }

    public void sendInfoToMainActivity (View view) {

        selectedTitle = et_title.getText().toString();
        selectedLocation = et_location.getText().toString();
        selectedHour = getIntent().getStringExtra("hour");
        selectedMinute = getIntent().getStringExtra("minute");
        selectedDate = getIntent().getStringExtra("date");

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", selectedTitle);
        intent.putExtra("hour", selectedHour);
        intent.putExtra("minute", selectedMinute);
        intent.putExtra("date", selectedDate);
        intent.putExtra("location", selectedLocation);
        Log.d("dato de maps a main", "enviado "+selectedTitle+selectedHour+selectedMinute+selectedDate+selectedLocation);
        finish();
        startActivityForResult(intent, 2);
    }
}
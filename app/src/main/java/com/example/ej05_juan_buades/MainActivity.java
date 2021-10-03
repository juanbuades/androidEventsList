package com.example.ej05_juan_buades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    String selectedTitle, selectedHour, selectedMinute, selectedDate, selectedLocation, selectedNewEvent;
    MyAdapter adapter;
    Marker marker;
    ListView list;
    ArrayList<String> eventos;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        list = findViewById(R.id.lista);

        eventos = new ArrayList<>();
        eventos.add("Hola");

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventos);
        list.setAdapter(adapter);

        putNewEvent();

    }
    /*public void addEventMarker(){

    }*/
    public void newEvent (View view){
        Intent intent = new Intent (this, HourDayActivity.class);
        startActivity(intent);
    }
    public void putNewEvent() {
        Intent intent = getIntent();
        selectedTitle = intent.getStringExtra("title");
        selectedHour = intent.getStringExtra("hour");
        selectedMinute = intent.getStringExtra("minute");
        selectedDate = intent.getStringExtra("date");
        selectedLocation = intent.getStringExtra("location");
        selectedNewEvent = String.valueOf("Evento: "+selectedTitle + " Hora: " + selectedHour + ":" + selectedMinute + " Dia: " + selectedDate + " Lugar: " + selectedLocation);
        if (selectedHour != null || selectedMinute != null || selectedDate != null){
            String newEvent = selectedNewEvent;
            eventos.add(selectedNewEvent);
        }

        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        adapter.notifyDataSetChanged();
    }
    public void addMarkerMap(){
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> adresses = geocoder.getFromLocationName(selectedLocation,1);
            if (adresses.size() !=0){
                Address addressSelected = adresses.get(0);
                LatLng position = new LatLng(addressSelected.getLatitude(), addressSelected.getLongitude());
                mMap.addMarker(new MarkerOptions().position(position));
            }else Toast.makeText(this,"No se encuentra la direccion",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng place = new LatLng(35,1);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
    }

    public void geoCode() {

    }
    class Event implements Serializable{
        String title;
        String hour;
        String minute;
        String date;
        String location;

        Event(String selectedTitle, String selectedHour, String selectedMinute, String selectedDate, String selectedLocation){

        }
    }

}
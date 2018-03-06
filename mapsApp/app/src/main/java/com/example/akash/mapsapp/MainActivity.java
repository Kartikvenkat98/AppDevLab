package com.example.akash.mapsapp;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap mgoogleMap;
    Button b;
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         b=findViewById(R.id.button);

        if(serviceAvail())
        {
            Toast.makeText(this,"Perfect",Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
            initmap();
        }
        else
        {

        }

    }

    private void initmap() {
        MapFragment mapFragment=(MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


    }

    public boolean serviceAvail()
    {
        GoogleApiAvailability apiAvailability=GoogleApiAvailability.getInstance();
        int flag=apiAvailability.isGooglePlayServicesAvailable(this);
        if(flag == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(apiAvailability.isUserResolvableError(0))
        {
            Dialog dialog=apiAvailability.getErrorDialog(this,0,0);
            dialog.show();


        }
        else
        {
            Toast.makeText(this,"Error in connecting",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mgoogleMap=googleMap;
        goToLocation(79.20,65.45);

    }

    private void goToLocation(double v, double v1) {
        LatLng ll=new LatLng(v,v1);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,5);
        mgoogleMap.moveCamera(update);
    }

    public void viewCountry(View v)
    {
        e=findViewById(R.id.editText);
        String country=e.getText().toString();

        Geocoder gc=new Geocoder(this);
        List<Address> list=null;
        try {
            list=gc.getFromLocationName(country,1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Address a=list.get(0);
        double lat = a.getLatitude();
        double lon=a.getLongitude();

        goToLocation(lat,lon);



    }
}

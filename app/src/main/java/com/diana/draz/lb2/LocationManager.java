package com.diana.draz.lb2;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationManager {
    private FusedLocationProviderClient fusedLocationClient;
    public boolean hasLocation;
    private double latitude;
    private double longitude;


    public String getLocation() {
        if (hasLocation) {
            return String.valueOf(latitude) + ", " + String.valueOf(longitude);
        } else {
            return "Геолокация недоступна";
        }

    }

    public void init(Activity context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(context, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            hasLocation = true;
                        }
                    }
                });
    }
}

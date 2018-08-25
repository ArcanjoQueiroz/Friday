package br.com.alexandre.friday;

import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

public abstract class LocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleApiClient googleApiClient;
    private Location location;
    private int locationRequestInterval;
    private int locationRequestFastestInterval;
    private float smallestDisplacement;

    private static final int REQUEST_LOCATION_PERMISSION = 504;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        final GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        builder.addApi(LocationServices.API);

        this.googleApiClient = builder.build();
        this.googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION }, REQUEST_LOCATION_PERMISSION);
        } else {
            final LocationRequest locationRequest = new LocationRequest();
            locationRequest.setSmallestDisplacement(smallestDisplacement);
            locationRequest.setInterval(locationRequestInterval);
            locationRequest.setFastestInterval(locationRequestFastestInterval);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiClient, locationRequest, this);

            this.location = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION: {
                if (grantResults[0] == 0) {
                    Log.i("Permission", "REQUEST_INTERNET_PERMISSION granted");
                }
                break;
            }
        }
    }

    @Override
    public void onLocationChanged(final Location location) {
        this.location = location;
     }

    @Override
    public void onConnectionSuspended(int i) {
        this.googleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("Connection failed:", " ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    protected Location getLocation() {
        return this.location;
    }

    protected int getLocationRequestInterval() {
        return locationRequestInterval;
    }

    protected void setLocationRequestInterval(int locationRequestInterval) {
        this.locationRequestInterval = locationRequestInterval;
    }

    protected int getLocationRequestFastestInterval() {
        return locationRequestFastestInterval;
    }

    protected void setLocationRequestFastestInterval(int locationRequestFastestInterval) {
        this.locationRequestFastestInterval = locationRequestFastestInterval;
    }

    protected float getSmallestDisplacement() {
        return smallestDisplacement;
    }

    protected void setSmallestDisplacement(float smallestDisplacement) {
        this.smallestDisplacement = smallestDisplacement;
    }
}

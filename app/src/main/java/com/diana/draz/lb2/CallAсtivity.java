package com.diana.draz.lb2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CallAсtivity<CallActivity> extends AppCompatActivity implements View.OnClickListener {

    public static final int PERMISSIONS_REQUEST_LOCATION = 99;

    private TextView _nameTextView;
    private TextView _phoneTextView;
    private Button _setPathButton;
    private Button _callTaxiButton;
    private TextView _summaryTextView;
    private LocationManager locationManager;

    private CallAсtivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        self = this;
        locationManager = new LocationManager();

        setTitle("Главная страница");

        _nameTextView = findViewById(R.id.name_recall);
        _phoneTextView = findViewById(R.id.phone_recall);
        _setPathButton = findViewById(R.id.set_path);
        _callTaxiButton = findViewById(R.id.call_taxi);
        _summaryTextView = findViewById(R.id.path);

        Bundle bundle = getIntent().getExtras();
        _nameTextView.setText(bundle.getString(LoginActivity.FIRSTNAME_KEY) + " " + bundle.getString(LoginActivity.SURNAME_KEY));
        _phoneTextView.setText(bundle.getString(LoginActivity.PHONE_KEY));

        _callTaxiButton.setEnabled(false);

        _setPathButton.setOnClickListener(this);
        _callTaxiButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission(this);
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent intent) {
        Bundle bundle = intent.getExtras();
        String result = bundle.getString(PathActivity.RESULT_KEY);
        _summaryTextView.setText(result);

        if (_summaryTextView.getText().length() > 0) {
           _callTaxiButton.setEnabled(true);
        } else {
            _callTaxiButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == _setPathButton) {
            Intent intent = new Intent(this, PathActivity.class);
            startActivityForResult(intent, PathActivity.RESULT_CODE);
        } else if (v == _callTaxiButton) {
            if (locationManager.hasLocation)
                Toast.makeText(this, "Такси выезжает, к " + locationManager.getLocation(), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Такси выезжает, геолокация недоступна", Toast.LENGTH_LONG).show();
        }

    }

    public boolean checkLocationPermission(final Activity self) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


                new AlertDialog.Builder(this)
                        .setTitle("Доступ к геолокации")
                        .setMessage("Приложению необходим доступ к геолокации")
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(
                                        self,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.init(self);
                    }

                } else {
                    Toast.makeText(self, "Геолокация недоступна", Toast.LENGTH_LONG);
                }
                return;
            }

        }
    }
}

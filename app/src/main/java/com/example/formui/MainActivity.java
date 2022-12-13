package com.example.formui;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CardView piCard, fdCard, odCard, pi_subCard, fd_subCard, od_subCard;
    LinearLayout piLayout, fdLayout, odLayout;
    Button getLoc;
    TextInputLayout address, city;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        piCard = findViewById(R.id.cvPersonalInformation);
        fdCard = findViewById(R.id.cvFinancialDetail);
        odCard = findViewById(R.id.cvOperationalDetail);
        pi_subCard = findViewById(R.id.cvSubPersonalInfo);
        fd_subCard = findViewById(R.id.cvSubFinancialDetail);
        od_subCard = findViewById(R.id.cvSubOperationalDetail);
        piLayout = findViewById(R.id.linearlayoutPreInfo);
        fdLayout = findViewById(R.id.linearlayoutFinancialDetail);
        odLayout = findViewById(R.id.linearlayoutOperationalDetail);
        getLoc = findViewById(R.id.btnSetLocation);
        address = findViewById(R.id.mdstore_Cmplt_Addrs);
        city = findViewById(R.id.md_Location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        getLoc.setOnClickListener(v -> {


            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // when permission granted
                getLocation();
            } else {
                //When permission denied
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        44);
            }

            Snackbar.make(v,"Location Set!",Snackbar.LENGTH_LONG).setAction("UNDO", v1 -> {
                address.getEditText().setText("");
                city.getEditText().setText("");
            }).show();


        });


    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {

            // Initialize location
            Location location = task.getResult();
            if (location != null) {
                try {
                    // Initialize geoCoder
                    Geocoder geocoder = new Geocoder(MainActivity.this,
                            Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);

                    address.getEditText().setText(addresses.get(0).getAddressLine(0));
                    city.getEditText().setText(addresses.get(0).getLocality());

                } catch (Exception handle) {
                    handle.printStackTrace();
                }
            }
        });
    }


    public void expand(View view) {
        int v = (pi_subCard.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(piLayout, new AutoTransition());
        pi_subCard.setVisibility(v);
    }


    public void expand1(View view) {
        int v = (fd_subCard.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(fdLayout, new AutoTransition());
        fd_subCard.setVisibility(v);
    }

    public void expand2(View view) {
        int v = (od_subCard.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(fd_subCard, new AutoTransition());
        od_subCard.setVisibility(v);

    }
}
package com.example.formui;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        logo = findViewById(R.id.imageLogo);
        appName = findViewById(R.id.textAppName);

        YoYo.with(Techniques.ZoomInDown).duration(2000).repeat(0).playOn(logo);
        YoYo.with(Techniques.ZoomInUp).duration(2000).repeat(0).playOn(appName);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },3000);




    }
}
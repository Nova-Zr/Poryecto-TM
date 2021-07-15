package com.test.controlcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    private TextView app;
    private Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = (ImageView) findViewById(R.id.logo_Im);
        app = (TextView) findViewById(R.id.logo_Tx);
        logo.animate().alpha(0).setDuration(1500);
        app.animate().alpha(0).setDuration(1500);
        (new Handler()).postDelayed(this::continuar, 1500);
    }
    public void continuar()
    {
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.b0);
        b2 = (Button) findViewById(R.id.b1);
        b3 = (Button) findViewById(R.id.b2);
        b4 = (Button) findViewById(R.id.b3);

        b1.setAlpha(0);
        b2.setAlpha(0);
        b3.setAlpha(0);
        b4.setAlpha(0);

        b1.animate().alpha(1).setDuration(300);
        b2.animate().alpha(1).setDuration(300);
        b3.animate().alpha(1).setDuration(300);
        b4.animate().alpha(1).setDuration(300);
    }
    public void registrarLayout(View view) {
        startActivity(new Intent(MainActivity.this, Registro.class));
    }
    public void ingresarLayout(View view) {
        startActivity(new Intent(MainActivity.this, Ingreso.class));
    }
    public void revisarLayout(View view) {
        startActivity(new Intent(MainActivity.this, Revisar.class));
    }
    public void instructorLayout(View view) {
        startActivity(new Intent(MainActivity.this, Instructor.class));
    }
}
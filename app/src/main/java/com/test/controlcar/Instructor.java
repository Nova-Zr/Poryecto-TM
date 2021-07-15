package com.test.controlcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.test.controlcar.Revisar.counter;

public class Instructor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
    }
    public void ListaInstructores(View view) {
        startActivity(new Intent(Instructor.this, ListaInstructores.class));
    }
    public void RegistrarInstructoresLayout(View view) {
        startActivity(new Intent(Instructor.this, RegistrarInstructor.class));
    }
}
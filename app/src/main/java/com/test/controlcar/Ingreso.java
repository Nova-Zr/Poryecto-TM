package com.test.controlcar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Ingreso extends AppCompatActivity {
    private DatabaseReference Usuarios;
    private TextView NumbHoldIn;
    private TextView Num, Pla, Hor;
    private String Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        NumbHoldIn = (TextView) findViewById(R.id.loginText);
        Usuarios = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String _Num = userSnapshot.child("numero").getValue(String.class);
                    String _Pla = userSnapshot.child("placa").getValue(String.class);
                    String _Hor = userSnapshot.child("hora").getValue(String.class);
                    NumbHoldIn.setText("");
                    setContentView(R.layout.datos);

                    Num = (TextView) findViewById(R.id.NumeroD);
                    Pla = (TextView) findViewById(R.id.PlacaD);
                    Hor = (TextView) findViewById(R.id.HoraD);
                    Num.setText("Numero   : " + _Num);
                    Pla.setText("Placa    : " + _Pla);
                    Hor.setText("Hora     : " + _Hor);
                }
            } else {
                Toast.makeText(getApplicationContext(), "No existe",
                        Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };


    ValueEventListener pagoListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                appleSnapshot.getRef().removeValue();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    public void ingresarUsuario(View view) {
        Search = NumbHoldIn.getText().toString();
        Usuarios.orderByChild("placa").equalTo(Search).addListenerForSingleValueEvent(valueEventListener);
    }
    public void pagarUsuario(View view) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("usuarios").orderByChild("placa").equalTo(Search);
        applesQuery.addListenerForSingleValueEvent(pagoListener);
        Toast.makeText(getApplicationContext(), "El auto due cancelado con exito",
                Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_ingreso);
    }
}
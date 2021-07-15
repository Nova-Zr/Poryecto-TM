package com.test.controlcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Registro extends AppCompatActivity {
    private DatabaseReference Usuarios;
    private TextView PlacaHold;
    private TextView NumbHold;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Usuarios = FirebaseDatabase.getInstance().getReference();
        PlacaHold = (TextView) findViewById(R.id.PlacaInput);
        NumbHold = (TextView) findViewById(R.id.NumeroInput);
        calendar = Calendar.getInstance();
    }
    public void registrarUsuario(View view) {
        String placa = PlacaHold.getText().toString();
        String numb = NumbHold.getText().toString();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minut = calendar.get(Calendar.MINUTE);
        String hour = Integer.toString(hora) + ":" + Integer.toString(minut);
        if(placa.length() > 0 && numb.length() > 0){
            String id = Usuarios.push().getKey();
            Usuario User = new Usuario(placa, numb, hour);
            Usuarios.child("usuarios").child(id).setValue(User);
            PlacaHold.setText("");
            NumbHold.setText("");
            Toast.makeText(getApplicationContext(),"Registro Completo",
                    Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(),"Debe llenar los espacios",
                    Toast.LENGTH_LONG).show();
        }
    }
}
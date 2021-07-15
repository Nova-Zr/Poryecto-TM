package com.test.controlcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarInstructor extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private TextView NombreHold;
    private TextView NumeroHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_instructor);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        NombreHold = (TextView) findViewById(R.id.NombreInstInput);
        NumeroHold = (TextView) findViewById(R.id.NumeroInstInput);
    }
    public void RegistrarInstructores(View view) {
        String nombre = NombreHold.getText().toString();
        String numero = NumeroHold.getText().toString();
        String disponible = "Disponible";
        if(nombre.length() > 0 && numero.length() > 0){
            //String id = databaseReference.push().getKey();
            Instructores Conductor = new Instructores(nombre, disponible, numero);
            databaseReference.child("instructores").child(nombre).setValue(Conductor);
            NombreHold.setText("");
            NumeroHold.setText("");
            Toast.makeText(getApplicationContext(),"Registro Completo",
                    Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(),"Debe llenar los espacios",
                    Toast.LENGTH_LONG).show();
        }
    }
}
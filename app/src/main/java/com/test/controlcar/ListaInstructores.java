package com.test.controlcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListaInstructores extends AppCompatActivity implements  View.OnClickListener {
    private DatabaseReference databaseReference;
    private DatabaseReference Usuarios;
    private TextView showCars;
    private View linearLayout;
    private String text;
    private Button clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_instructores);
        linearLayout = findViewById(R.id.listaInstructor);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("instructores").addListenerForSingleValueEvent(valueEventListener);
        Usuarios = FirebaseDatabase.getInstance().getReference("instructores");
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        clicked = (Button)v;
        text = b.getText().toString();
        Usuarios.orderByChild("nombre").equalTo(text).addListenerForSingleValueEvent(CambiarDisponible);
        //databaseReference.child("instructores").child(text).child("disponible").setValue("No Disponible");
    }

    ValueEventListener valueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot){
            for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                Button b1 = new Button(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(3,25,0,0);
                b1.setLayoutParams(params);
                String Disponible = userSnapshot.child("disponible").getValue(String.class);
                if(Disponible.equals("Disponible"))
                    b1.setBackgroundResource(R.drawable.button_green);
                if(Disponible.equals("No Disponible"))
                    b1.setBackgroundResource(R.drawable.button_red);
                b1.setText(userSnapshot.child("nombre").getValue(String.class));
                b1.setTextSize(25);
                b1.setTextColor(getResources().getColor(R.color.black));
                b1.setOnClickListener(ListaInstructores.this::onClick);
                ((LinearLayout) linearLayout).addView(b1);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    ValueEventListener CambiarDisponible = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                String _Libre = userSnapshot.child("disponible").getValue(String.class);
                if(_Libre.equals("Disponible"))
                {
                    databaseReference.child("instructores").child(text).child("disponible").setValue("No Disponible");
                    clicked.setBackgroundResource(R.drawable.button_red);
                }
                if(_Libre.equals("No Disponible"))
                {
                    databaseReference.child("instructores").child(text).child("disponible").setValue("Disponible");
                    clicked.setBackgroundResource(R.drawable.button_green);
                }

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
}
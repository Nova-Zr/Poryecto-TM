package com.test.controlcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Revisar extends AppCompatActivity {
    public static int counter = 0;
    private DatabaseReference databaseReference;
    private TextView showCars;
    private View linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar);
        showCars = (TextView) findViewById(R.id.autosText);
        linearLayout = findViewById(R.id.linearL);
        showCars.setText("Autos en Uso : 0");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("usuarios").addListenerForSingleValueEvent(valueEventListener);

    }
    ValueEventListener valueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot){
            counter = (int)dataSnapshot.getChildrenCount();
            showCars.setText("Autos en Uso : " + String.valueOf(counter));
            for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                TextView textView = new TextView(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(3,25,0,0);
                textView.setLayoutParams(params);

                textView.setText("Numero de Placa : " + userSnapshot.child("placa").getValue(String.class));
                textView.setTextSize(25);
                textView.setTextColor(getResources().getColor(R.color.black));
                ((LinearLayout) linearLayout).addView(textView);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };
}
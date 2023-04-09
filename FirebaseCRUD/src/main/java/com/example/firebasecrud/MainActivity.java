package com.example.firebasecrud;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btSaveScore, btFetchScore;
    EditText etEnterScore;
    DatabaseReference ref;
    FirebaseDatabase db;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSaveScore = findViewById(R.id.btSaveScore);
        btFetchScore = findViewById(R.id.btFetchScore);
        etEnterScore = findViewById(R.id.etEnterScore);
    }

    protected void onStart() {
        super.onStart();
        // Fetch Firebase reference
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("score");
    }

    protected void onResume() {
        super.onResume();
        btSaveScore.setOnClickListener(view -> writeToFirebase());
        btFetchScore.setOnClickListener(view -> readFromFirebase());
    }

    // Storing the score in Firebase
    protected void writeToFirebase() {
        score = Integer.parseInt(etEnterScore.getText().toString());
        Toast.makeText(MainActivity.this, "You entered: " + score, Toast.LENGTH_SHORT).show();
        ref.setValue(score).addOnFailureListener(e -> {
            // Write to Firebase failed
            Toast.makeText(MainActivity.this, "Write to Firebase failed!", Toast.LENGTH_SHORT).show();
        });
    }

    // Reading the score from Firebase
    protected void readFromFirebase() {
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int score = dataSnapshot.getValue(Integer.class);
                Toast.makeText(MainActivity.this, "Value is: " + score, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Failed to read value!" + score, Toast.LENGTH_SHORT).show();
                Log.w("MainActivity", "Failed to read value!", error.toException());
            }
        });
    }
}
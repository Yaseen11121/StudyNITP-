package com.mohyaseensidd.studynitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class profilePage extends AppCompatActivity {

    EditText nameEditText, emailEditText, mobileEditText, rollEditText, branchEditText, semesterEditText;

    Button updateButton;
    DatabaseReference userRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backbtn);
        getSupportActionBar().setTitle("Profile");

        // Linking UI
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        rollEditText = findViewById(R.id.rollEditText);
        branchEditText = findViewById(R.id.branchEditText);
        semesterEditText = findViewById(R.id.semesterEditText);
        updateButton = findViewById(R.id.updateButton);

        // Firebase references
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String email = user.getEmail();

        // Get the database reference
        userRef = FirebaseDatabase.getInstance().getReference("users");

        // Fetch and set data
        userRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnap : snapshot.getChildren()) {
                        String name = userSnap.child("name").getValue(String.class);
                        String mobNo = userSnap.child("mobNo").getValue(String.class);
                        String rollNo = userSnap.child("rollNo").getValue(String.class);
                        String branch = userSnap.child("branch").getValue(String.class);
                        String semester = userSnap.child("semester").getValue(String.class);

                        // Set in EditTexts
                        nameEditText.setText(name);
                        emailEditText.setText(email);
                        mobileEditText.setText(mobNo);
                        rollEditText.setText(rollNo);
                        branchEditText.setText(branch);
                        semesterEditText.setText(semester);
                        updateButton.setOnClickListener(v -> {
                            String newName = nameEditText.getText().toString().trim();
                            String newMobNo = mobileEditText.getText().toString().trim();
                            String newRollNo = rollEditText.getText().toString().trim();
                            String newBranch = branchEditText.getText().toString().trim();
                            String newSemester = semesterEditText.getText().toString().trim();

                            // Update map
                            HashMap<String, Object> updates = new HashMap<>();
                            updates.put("name", newName);
                            updates.put("mobNo", newMobNo);
                            updates.put("rollNo", newRollNo);
                            updates.put("branch", newBranch);
                            updates.put("semester", newSemester);

                            userRef.child(Objects.requireNonNull(userSnap.getKey())).updateChildren(updates)
                                    .addOnSuccessListener(aVoid ->
                                            Toast.makeText(profilePage.this, "Profile Updated!", Toast.LENGTH_SHORT).show()
                                    )
                                    .addOnFailureListener(e ->
                                            Toast.makeText(profilePage.this, "Update Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                                    );
                        });

                    }


                } else {
                    Toast.makeText(profilePage.this, "User data not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profilePage.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homebtn, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back
            return true;
        }else if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
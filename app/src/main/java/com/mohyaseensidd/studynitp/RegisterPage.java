package com.mohyaseensidd.studynitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {
    EditText signupRollNoInput,signupemailInput, signuppasswordInput, signupconfirmPasswordInput, signupNameInput, signupMobNoInput;
    String signupRollNo,signupemail, signupbranch, signuppassword, signupconfirmPassword, signupSem, signupName, signupMobNo;
    Button signUpBtn;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    Spinner semSelectInput,signupbranchInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        signupRollNoInput = findViewById(R.id.rollNoInput);
        signupNameInput = findViewById(R.id.nameInput);
        signupemailInput = findViewById(R.id.emailInput);
        signupbranchInput = findViewById(R.id.branchInput);
        signupMobNoInput = findViewById(R.id.mobNoInput);
        signuppasswordInput = findViewById(R.id.passwordInput);
        signupconfirmPasswordInput = findViewById(R.id.confirmPasswordinput);
        signUpBtn = findViewById(R.id.signUpButton);

        semSelectInput = findViewById(R.id.semInput);
        String[] semesters = {"Select Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
        String[] branches = {"Select Branch", "ECE", "CSE", "EEE", "ME", "CE", "Architecture"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesters);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branches);
        semSelectInput.setAdapter(adapter1);
        signupbranchInput.setAdapter(adapter2);

        semSelectInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                signupSem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // optional
            }
        });

        signupbranchInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                signupbranch = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // optional
            }
        });


        signUpBtn.setOnClickListener(v -> registerUser());


    }
    private void registerUser() {
        signupRollNo = signupRollNoInput.getText().toString().trim();
        signupemail = signupemailInput.getText().toString().trim();
        signuppassword = signuppasswordInput.getText().toString().trim();
        signupconfirmPassword = signupconfirmPasswordInput.getText().toString().trim();
        signupName = signupNameInput.getText().toString().trim();
        signupMobNo = signupMobNoInput.getText().toString().trim();
        signupconfirmPassword = signupconfirmPasswordInput.getText().toString().trim();
        signupconfirmPassword = signupconfirmPasswordInput.getText().toString().trim();


        if (signupRollNo.isEmpty() || signupemail.isEmpty() || signupbranch.isEmpty() || signuppassword.isEmpty() || signupconfirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (signupbranch.equals("Select Branch") || signupSem.equals("Select Semester")) {
            Toast.makeText(this, "Please select valid Branch and Semester", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!signuppassword.equals(signupconfirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase auth create user
        mAuth.createUserWithEmailAndPassword(signupemail, signuppassword).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String userId = signupRollNo; // ya mAuth.getCurrentUser().getUid()


                User user = new User(
                        signupRollNo,
                        signupName,
                        signupemail,
                        signupbranch,
                        signupSem,
                        signupMobNo
                );


                mDatabase.child(userId).setValue(user).addOnCompleteListener(dbTask -> {
                    if (dbTask.isSuccessful()) {
                        Toast.makeText(this, "Account created & data saved!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Data save failed: " + dbTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            else {
                Toast.makeText(this, "Auth failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void openLoginPage(View view){
        Toast.makeText(this,"Login!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
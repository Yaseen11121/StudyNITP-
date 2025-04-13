package com.mohyaseensidd.studynitp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DSP_Book_Select_EC_sem4 extends AppCompatActivity {
    private List<book> BookList;

    private RecyclerBookAdapter adapter;

    private RecyclerView recyclerView;

    ImageView uploadBk;
    private static final int PICK_PDF_REQUEST = 1;

    private String enteredTitle = "", enteredAuthor = "";
    private Uri pdfUri;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dsp_book_select_ec_sem4);
        RelativeLayout rlb = findViewById(R.id.rlb);


        BookList = new ArrayList<>();
        BookList.add(new book("Digital Signal Processing","S.Salivahanan",R.drawable.img,"https://firebasestorage.googleapis.com/v0/b/login-register-firebase-8e298.firebasestorage.app/o/S.%20Salivahanan-DSP.pdf?alt=media&token=97ef90f2-8efa-4591-b92b-6fef1edde7ef"));
        BookList.add(new book("Digital Signal Processing","John G. Proakis X Dimitris G. Manolakis",R.drawable.img,"https://firebasestorage.googleapis.com/v0/b/login-register-firebase-8e298.firebasestorage.app/o/digital_signal_processing_principles_algorithms_and_applications_third_edition.pdf?alt=media&token=b1ce6c4e-0971-4db6-89c1-e87d65d9aba3"));

        recyclerView = findViewById(R.id.bookrecyclerView);
        uploadBk = findViewById(R.id.bookUpload);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerBookAdapter(this, BookList, this::showDeleteDialog);
        recyclerView.setAdapter(adapter);

        DatabaseReference booksRef = FirebaseDatabase.getInstance().getReference("books");

        booksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                BookList.clear();
                for (DataSnapshot bookSnap : snapshot.getChildren()) {
                    book Book = bookSnap.getValue(book.class);
                    BookList.add(Book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DSP_Book_Select_EC_sem4.this, "Failed to load books", Toast.LENGTH_SHORT).show();
            }
        });



        uploadBk.setOnClickListener(v -> {
            Toast.makeText(this,"plus clicked",Toast.LENGTH_SHORT).show();
            showInputDialog();

        });

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backbtn);
        getSupportActionBar().setTitle("Books");

        AnimationDrawable animationDrawable = (AnimationDrawable) rlb.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
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

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_upload_book, null);
        builder.setView(dialogView);

        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etAuthor = dialogView.findViewById(R.id.etAuthor);

        builder.setTitle("Add Book");
        builder.setPositiveButton("Next", (dialog, which) -> {
            enteredTitle = etTitle.getText().toString().trim();
            enteredAuthor = etAuthor.getText().toString().trim();

            if (!enteredTitle.isEmpty() && !enteredAuthor.isEmpty()) {
                openFilePicker();
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pdfUri = data.getData();
            uploadPDFAndSaveBook();
        }
    }

    private void uploadPDFAndSaveBook() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading...");
        dialog.show();

        String fileName = System.currentTimeMillis() + ".pdf";
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("books/" + fileName);

        storageRef.putFile(pdfUri)
                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    dialog.dismiss();

                    book Book = new book(enteredTitle, enteredAuthor, R.drawable.img, uri.toString());
                    FirebaseDatabase.getInstance().getReference("books").push().setValue(Book);

                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                }))
                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Upload Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void showDeleteDialog(book selectedBook) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Book")
                .setMessage("Are you sure you want to delete this book?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    deleteBookFromFirebase(selectedBook);
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteBookFromFirebase(book selectedBook) {
        DatabaseReference booksRef = FirebaseDatabase.getInstance().getReference("books");

        booksRef.orderByChild("pdfUrl").equalTo(selectedBook.getPdfUrl())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot bookSnap : snapshot.getChildren()) {
                            bookSnap.getRef().removeValue(); // Delete from Firebase
                        }
                        Toast.makeText(DSP_Book_Select_EC_sem4.this, "Book deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DSP_Book_Select_EC_sem4.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
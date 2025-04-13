package com.mohyaseensidd.studynitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DSP_Experiment_sem4 extends AppCompatActivity {
    private RecyclerView rvExperiments;
    private RecyclerExperimentAdapter adapter;
    private List<experiment> experimentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dsp_experiment_sem4);
        Toolbar toolbar = findViewById(R.id.expToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backbtn);
        getSupportActionBar().setTitle("Experiments");

        rvExperiments = findViewById(R.id.rvExperiments);

        experimentList = new ArrayList<>();
        experimentList.add(new experiment(
                "Experiment 1",
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                "comingsoon"
        ));
        experimentList.add(new experiment(
                "Experiment 2",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/1E7X4xBob7v8xHtGREx5UpB6C6H4m2zqX/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 3",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "comingsoon"
        ));
        experimentList.add(new experiment(
                "Experiment 4",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/15BxX6aLYtbpbTTRkaPEiVbsYzWeu4D_n/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 5",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/14FxQGiktto2d3uMzndoF6_tQtqcJ4O6h/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 6",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/139G10MmZRioCsxkBvcZMlscuoq77LX7y/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 7",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "comingsoon"
        ));
        experimentList.add(new experiment(
                "Experiment 8",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/18iEQZ9Y61IUsA-Yv078_iA14WtxvZus7/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 9",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/18iEQZ9Y61IUsA-Yv078_iA14WtxvZus7/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 10",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/18iEQZ9Y61IUsA-Yv078_iA14WtxvZus7/view?usp=sharing"
        ));
        experimentList.add(new experiment(
                "Experiment 11",
                "https://www.youtube.com/watch?v=O91DT1pR1ew",
                "https://drive.google.com/file/d/18iEQZ9Y61IUsA-Yv078_iA14WtxvZus7/view?usp=sharing"
        ));

        adapter = new RecyclerExperimentAdapter(this, experimentList);
        rvExperiments.setLayoutManager(new LinearLayoutManager(this));
        rvExperiments.setAdapter(adapter);



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
package com.mohyaseensidd.studynitp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    List<Subject> subjectList;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    ListView navList;
    ArrayList<Nav_item> navItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);


        setDrawer();

        drawerLayout = findViewById(R.id.drawer_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) drawerLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        subjectList = new ArrayList<>();
        subjectList.add(new Subject("Digital Signal Processing",R.drawable.dsp_img));
        subjectList.add(new Subject("Digital IC Design",R.drawable.dic_img));
        subjectList.add(new Subject("Seminar & Technical Report",R.drawable.seminar_img));
        subjectList.add(new Subject("Microprocessor & Microcontroller",R.drawable.mpmc_img));
        subjectList.add(new Subject("Electromagnetic Field Theory",R.drawable.emft_img));
        subjectList.add(new Subject("Linear Integrated Circuits",R.drawable.lic_img));
        subjectList.add(new Subject("Support",R.drawable.support_img));


        recyclerView = findViewById(R.id.gridRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setAdapter(new RecyclerHomePageAdapter(subjectList,this));

        ImageView profileImg = findViewById(R.id.profileIcon);
        profileImg.setOnClickListener(v->{
            Intent intent = new Intent(this,loadingTransition.class);
            startActivity(intent);
        });
    }

    private void setDrawer(){

        navList = findViewById(R.id.nav_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navItems = new ArrayList<>();
        navItems.add(new Nav_item("Help", R.drawable.help_ic));
        navItems.add(new Nav_item("Store", R.drawable.blog_ic));
        navItems.add(new Nav_item("Support Us", R.drawable.support_ic));
        navItems.add(new Nav_item("Reset Password", R.drawable.resetpass_ic));
        navItems.add(new Nav_item("Logout", R.drawable.logout_ic));

        NavAdapter adapter = new NavAdapter(this, navItems);
        navList.setAdapter(adapter);

        navList.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Clicked: " + navItems.get(position).title, Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
        });
    }
}
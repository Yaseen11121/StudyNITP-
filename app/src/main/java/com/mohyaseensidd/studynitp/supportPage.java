package com.mohyaseensidd.studynitp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Arrays;
import java.util.List;


public class supportPage extends AppCompatActivity {
    class BoxData {
        String title;
        List<String> emails;
        int boxColor;
        int titleColor;
        int emailColor;

        BoxData(String title, List<String> emails, int boxColor, int titleColor, int emailColor) {
            this.title = title;
            this.emails = emails;
            this.boxColor = boxColor;
            this.titleColor = titleColor;
            this.emailColor = emailColor;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support_page);
        LinearLayout container = findViewById(R.id.containerLayout);

        Toast.makeText(this,"Click link to mail!!",Toast.LENGTH_SHORT).show();

        // Updated with lighter background colors
        List<BoxData> data = Arrays.asList(
                new BoxData("AR Academic", Arrays.asList("acad.help@nitp.ac.in"),
                        Color.parseColor("#FFF9C4"), Color.parseColor("#37474F"), Color.parseColor("#1E88E5")),
                new BoxData("Shivam Maurya\n(App Developer)", Arrays.asList("shivamm.ug23.ec@nitp.ac.in"),
                        Color.parseColor("#C8E6C9"), Color.parseColor("#1B5E20"), Color.parseColor("#2E7D32")),
                new BoxData("Sourabh Pandey\n(App Developer)", Arrays.asList("sourabhp.ug23.ec@nitp.ac.in"),
                        Color.parseColor("#FFCCBC"), Color.parseColor("#BF360C"), Color.parseColor("#D84315")),
                new BoxData("Moh Yaseen Siddiqui\n(App Developer)", Arrays.asList("mohs.ug23.ec@nitp.ac.in"),
                        Color.parseColor("#E1BEE7"), Color.parseColor("#4A148C"), Color.parseColor("#6A1B9A"))
        );

        // Add boxes dynamically
        for (BoxData box : data) {
            addBox(container, box);
        }

    }
    private void addBox(LinearLayout parent, BoxData box) {
        // Create CardView for each box
        CardView cardView = new CardView(this);

        // Set margin (20dp bottom spacing between cards)
        int marginDp = 20;
        int marginPx = (int) (getResources().getDisplayMetrics().density * marginDp);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, marginPx);
        cardView.setLayoutParams(cardParams);

        cardView.setCardElevation(15f); // Shadow
        cardView.setRadius(50f); // Rounded corners
        cardView.setUseCompatPadding(true);
        cardView.setCardBackgroundColor(box.boxColor); // Background color

        // Content layout inside card
        LinearLayout boxLayout = new LinearLayout(this);
        boxLayout.setOrientation(LinearLayout.VERTICAL);
        boxLayout.setPadding(24, 24, 24, 24);

        // Title
        TextView titleView = new TextView(this);
        titleView.setText(box.title);
        titleView.setTextSize(18f);
        titleView.setTextColor(box.titleColor);
        titleView.setPadding(0, 0, 0, 16);
        boxLayout.addView(titleView);

        // Email links
        for (String email : box.emails) {
            TextView emailView = new TextView(this);
            emailView.setText(email);
            emailView.setTextColor(box.emailColor);
            emailView.setPadding(0, 8, 0, 8);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                startActivity(Intent.createChooser(intent, "Send Email"));
            });

            boxLayout.addView(emailView);
        }

        // Assemble card
        cardView.addView(boxLayout);
        parent.addView(cardView);
    }

}
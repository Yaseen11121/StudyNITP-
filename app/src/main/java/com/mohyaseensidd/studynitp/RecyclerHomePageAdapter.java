package com.mohyaseensidd.studynitp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerHomePageAdapter extends RecyclerView.Adapter<RecyclerHomePageAdapter.SubjectViewHolder> {

    private List<Subject> subjects;
    private Context context;

    public RecyclerHomePageAdapter(List<Subject> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }

    @Override
    public @NonNull SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.textView.setText(subjects.get(position).getName());
        holder.img.setImageResource(subjects.get(position).getSubImg());
        switch(position){
            case 0 :

            case 1 :
            case 5 :
            case 4 :
            case 3 :
            case 2 :
                holder.subCard.setOnClickListener(v->{
                    Intent intent = new Intent(context,Select_ECE_Sem4.class);
                    context.startActivity(intent);
                });
                break;

            case 6 :
                holder.subCard.setOnClickListener(v->{
                    Intent intent = new Intent(context,supportPage.class);
                    context.startActivity(intent);
                });
        }
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        CardView subCard;

        TextView textView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cardBackgroundImage);
            subCard = itemView.findViewById(R.id.subCard);
            textView = itemView.findViewById(R.id.subjectNameTextView);
        }
    }
}

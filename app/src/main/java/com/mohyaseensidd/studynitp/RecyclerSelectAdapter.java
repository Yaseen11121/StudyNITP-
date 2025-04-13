package com.mohyaseensidd.studynitp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerSelectAdapter extends RecyclerView.Adapter<RecyclerSelectAdapter.SelectViewHolder>{
    private final List<String> selection;
    private final Context context;

    public RecyclerSelectAdapter(List<String> subjects, Context context) {
        this.selection = subjects;
        this.context = context;
    }

    @NonNull
    @Override

    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select, parent, false);
        return new SelectViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        String subject = selection.get(position);
        holder.textView.setText(subject);
        switch(position){
            case 0 :
                holder.card.setOnClickListener(v->{
                    Intent intent = new Intent(context, DSP_Book_Select_EC_sem4.class);
                    context.startActivity(intent);
                });
                break;
            case 1 :
            case 2 :
            case 3 :
            case 4 :
                holder.card.setOnClickListener(v->{
                    Toast.makeText(context,"Coming Soon!!",Toast.LENGTH_SHORT).show();
                });
                break;
            case 5 :
//                holder.card.setOnClickListener(v->{
//                    Intent intent = new Intent(context, DSP_Experiment_sem4.class);
//                    context.startActivity(intent);
//                });
        }

    }

    @Override
    public int getItemCount() {
        return selection.size();
    }

    public static class SelectViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView card;

        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewSubject);
            card = itemView.findViewById(R.id.ithSubject);
        }
    }

}

package com.mohyaseensidd.studynitp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerExperimentAdapter extends RecyclerView.Adapter<RecyclerExperimentAdapter.ViewHolder> {

    private Context context;
    private List<experiment> experimentList;

    public RecyclerExperimentAdapter(Context context, List<experiment> experimentList) {
        this.context = context;
        this.experimentList = experimentList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivYoutube, ivPdf;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvExperimentName);
            ivYoutube = itemView.findViewById(R.id.ivYoutube);
            ivPdf = itemView.findViewById(R.id.ivPdf);
        }
    }

    @Override
    public RecyclerExperimentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_experiment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerExperimentAdapter.ViewHolder holder, int position) {
        experiment exp = experimentList.get(position);
        holder.tvName.setText(exp.getName());



        switch(position){
            case 1:
            case 3 :
            case 4 :
            case 5 :
                holder.ivYoutube.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exp.getYtUrl()));
                    context.startActivity(intent);
                });

                holder.ivPdf.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exp.getPdfUrl()));
                    context.startActivity(intent);
                });
                break;
            default:
                holder.ivYoutube.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exp.getYtUrl()));
                    context.startActivity(intent);
                });

                holder.ivPdf.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(exp.getPdfUrl()));
                    Intent intent = new Intent(context, ComingSoon.class);
                    context.startActivity(intent);
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return experimentList.size();
    }
}
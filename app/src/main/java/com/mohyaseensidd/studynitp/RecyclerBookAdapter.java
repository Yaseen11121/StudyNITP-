package com.mohyaseensidd.studynitp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerBookAdapter extends RecyclerView.Adapter<RecyclerBookAdapter.BookHolder> {

    Context context;

    List<book> books;
    private final OnItemLongClickListener longClickListener;

    public RecyclerBookAdapter(Context context, List<book> books,OnItemLongClickListener longClickListener) {
        this.context = context;
        this.books = books;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public RecyclerBookAdapter.BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book,parent,false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBookAdapter.BookHolder holder, int position) {
        holder.bkImg.setImageResource(R.drawable.img);
        holder.bkName.setText(books.get(position).title);
        holder.athName.setText(books.get(position).author);
        holder.llrow.setOnClickListener(v->{
            Toast.makeText(context,"You tried to open "+books.get(position).title+" by "+books.get(position).author,Toast.LENGTH_SHORT).show();
            String url = books.get(position).pdfUrl;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                context.startActivity(Intent.createChooser(intent, "Open PDF using"));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show();
            }
        });
        holder.llrow.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(books.get(position));
                Toast.makeText(context, "Hold to delete this book", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder{
        ImageView bkImg;
        TextView bkName, athName;
        LinearLayout llrow;
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bkName =itemView.findViewById(R.id.bookName);
            athName =itemView.findViewById(R.id.bookAuthor);
            bkImg = itemView.findViewById(R.id.bookImage);
            llrow = itemView.findViewById(R.id.ithbook);
        }
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(book selectedBook);
    }

}

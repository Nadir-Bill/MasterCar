package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {


    private Context ctx;
    private ArrayList<ExampleItem> exampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        this.ctx = context;
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.example_view, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem = exampleList.get(position);
        holder.mTextViewCreator.setText("Likes : "+currentItem.getmCreator());
        holder.likes.setText(currentItem.getmLikes());
        //Picasso.with(ctx).load(currentItem.getImgUrl()).fit().centerInside().into(holder.mImageView);
        //Picasso.get().load(exampleList.get(position).getImgUrl()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextViewCreator;
        public TextView likes;
        public ImageView mImageView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_View_creator);
            likes = itemView.findViewById(R.id.text_view_likes);
        }
    }

}

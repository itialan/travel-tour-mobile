package com.example.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelapp.MainActivity;
import com.example.travelapp.R;
import com.example.travelapp.model.Tour;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {

    Context context;
    List<Tour> tourList;

    public TourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tours_row_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        holder.tourName.setText(tourList.get(position).getName());
        holder.countryName.setText(tourList.get(position).getLocation());
        holder.price.setText(String.valueOf(tourList.get(position).getPrice()));
        //holder.tourImage.setImageResource(tourList.get(position).getImageUrl());
        Glide.with(context).load("https://bookingtour.herokuapp.com/template/upload/tour/" + tourList.get(position).getImageCover()).into(holder.tourImage);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public static final class TourViewHolder extends RecyclerView.ViewHolder {
        ImageView tourImage;
        TextView tourName, countryName, price;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            tourImage = itemView.findViewById(R.id.place_image);
            tourName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}

package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.travelapp.adapter.TourAdapter;
import com.example.travelapp.api.ApiService;
import com.example.travelapp.model.Tour;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView tourRecycler;
    TourAdapter tourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookingtour.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Tour> tours = apiService.getTours();
        tours.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Tour tour = response.body();

                setTourRecycler(tour.getListResult());
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTourRecycler(List<Tour> tourList) {
        tourRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tourRecycler.setLayoutManager(layoutManager);
        tourAdapter = new TourAdapter(this, tourList);
        tourRecycler.setAdapter(tourAdapter);
    }
}
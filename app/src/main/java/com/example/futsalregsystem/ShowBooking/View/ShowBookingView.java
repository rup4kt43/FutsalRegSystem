package com.example.futsalregsystem.ShowBooking.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.futsalregsystem.R;
import com.example.futsalregsystem.ShowBooking.Adapter.ShowBookingAdapter;
import com.example.futsalregsystem.ShowBooking.Contracts.ShowBookingContracts;
import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;
import com.example.futsalregsystem.ShowBooking.Presenter.ShowBookingPresenter;

import java.util.ArrayList;

public class ShowBookingView extends AppCompatActivity implements ShowBookingContracts.view {

    RecyclerView recyclerView;
    private ShowBookingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_showbooking);


        getSupportActionBar().setTitle("Booking");

        presenter = new ShowBookingPresenter(this);
        presenter.loadBookingList();

        recyclerView = findViewById(R.id.rv_admin_showBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void loadBookingList(ArrayList<ShowBookingDTO> bookingList) {
        ShowBookingAdapter showBookingAdapter = new ShowBookingAdapter(this, bookingList);
        recyclerView.setAdapter(showBookingAdapter);
    }
}

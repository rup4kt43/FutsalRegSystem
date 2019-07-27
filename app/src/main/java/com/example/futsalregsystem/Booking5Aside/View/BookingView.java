package com.example.futsalregsystem.Booking5Aside.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futsalregsystem.Booking5Aside.Adapters.BookingAdapter;
import com.example.futsalregsystem.Booking5Aside.Contracts.BookingContracts;
import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;
import com.example.futsalregsystem.Booking5Aside.Presenter.BookingPresenter;
import com.example.futsalregsystem.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BookingView extends AppCompatActivity implements BookingContracts.view {
    private BookingPresenter bookingPresenter;
    private RecyclerView recyclerView;
    public BookingAdapter bookingAdapter;
    private String date;
    private TextView tv_date;
    private TextView day;
    private ArrayList<BookingDTO> adapterList;
    private CoordinatorLayout coordinatorLayout;
    private ArrayList<BookingDTO> list;
    public static String side;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);


        coordinatorLayout = findViewById(R.id.cl_bookingView);
        day = findViewById(R.id.tv_day);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_booking_toolbar);
        getSupportActionBar().setElevation(10);

        TextView title = findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Booking");

        //Recieving the selected Date
        Intent i = getIntent();
        date = i.getStringExtra("selectedDate");
        side = i.getStringExtra("selectedSide");

        //Setting the date in the booking view
        tv_date = findViewById(R.id.tv_date);
        tv_date.setText(date);

        //Getting day from date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        //Invoking the presenter
        bookingPresenter = new BookingPresenter(this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loadPresnterSchedule(date);
            }
        },0,1000);



        //Refrencing recyclerView and setting properties
        recyclerView = findViewById(R.id.rv_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adding a divider line
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


    }

    public void loadPresnterSchedule(String date) {
        bookingPresenter.loadSchedule(date);
    }


    //BookingView contract implemented methods

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadSchedule(ArrayList<BookingDTO> scheduleList) {

        this.list = scheduleList;
        setAdapter();
    }

    private void setAdapter() {

        bookingAdapter = new BookingAdapter(BookingView.this, list, date,side);
        recyclerView.setAdapter(bookingAdapter);


    }
}

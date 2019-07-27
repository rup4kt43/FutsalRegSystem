package com.example.futsalregsystem.MyBooking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.futsalregsystem.R;

import java.util.ArrayList;

public class BookingAdapter  extends RecyclerView.Adapter{
    private final MyBooking context;
    private final ArrayList<BookingDTO> myBook;
    private TextView date,time,side;

    public BookingAdapter(MyBooking myBooking, ArrayList<BookingDTO> myBooking1) {

        this.context = myBooking;
        this.myBook = myBooking1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mybooking_containner,viewGroup,false);
        return new myBookingHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            date.setText(myBook.get(i).getDate());
            time.setText(myBook.get(i).getTime());
            side.setText(myBook.get(i).getSide());
    }

    @Override
    public int getItemCount() {
        return myBook.size();
    }

    private class myBookingHoler extends RecyclerView.ViewHolder {
        public myBookingHoler(View view) {
            super(view);
            date = view.findViewById(R.id.tv_mb_date);
            time = view.findViewById(R.id.tv_mb_time);
            side = view.findViewById(R.id.tv_mb_side);
        }
    }
}

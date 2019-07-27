package com.example.futsalregsystem.ShowBooking.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.futsalregsystem.R;
import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;
import com.example.futsalregsystem.ShowBooking.View.ShowBookingView;

import java.util.ArrayList;

public class ShowBookingAdapter extends RecyclerView.Adapter {
    private final ShowBookingView context;
    private final ArrayList<ShowBookingDTO> bookingList;
    private TextView date, name, time, contact, side;

    public ShowBookingAdapter(ShowBookingView showBookingView, ArrayList<ShowBookingDTO> bookingList) {

        this.context = showBookingView;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.showbooking_containner, viewGroup, false);
        return new CustomBookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        date.setText(bookingList.get(i).getDate());
        time.setText(bookingList.get(i).getTime());
        contact.setText(bookingList.get(i).getContact());
        name.setText(bookingList.get(i).getUsername());

        String fullSide = bookingList.get(i).getSide();
        String cropSide = fullSide.length() < 2 ? fullSide : fullSide.substring(0, 2);



        side.setText(cropSide);

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    private class CustomBookingHolder extends RecyclerView.ViewHolder {
        public CustomBookingHolder(View view) {
            super(view);
            date = view.findViewById(R.id.tv_sb_containner_date);
            name = view.findViewById(R.id.tv_sb_containner_name);
            time = view.findViewById(R.id.tv_sb_containner_time);
            contact = view.findViewById(R.id.tv_sb_containner_contact);
            side = view.findViewById(R.id.tv_sb_containner_side);
        }
    }
}

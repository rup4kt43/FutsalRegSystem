package com.example.futsalregsystem.ShowBooking.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.ShowBooking.Contracts.ShowBookingContracts;
import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowBookingFirebaseDB {
    public void loadBookingList(final ShowBookingContracts.modelFirebaseCallBack callBack) {

        final ArrayList<ShowBookingDTO> bookingList = new ArrayList<>();
        DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference().child("Booking");

        bookingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingList.clear();
                for (DataSnapshot dateRef : dataSnapshot.getChildren()) {
                    String date = dateRef.getKey();
                    for (DataSnapshot timeRef : dateRef.getChildren()) {
                        ShowBookingDTO showBookingDTO = new ShowBookingDTO();
                        String time = timeRef.getKey();
                        String name = String.valueOf(timeRef.child("Username").getValue());
                        String contact = String.valueOf(timeRef.child("Phone").getValue());
                        String side = String.valueOf(timeRef.child("Side").getValue());

                        showBookingDTO.setDate(date);
                        showBookingDTO.setTime(time);
                        showBookingDTO.setContact(contact);
                        showBookingDTO.setUsername(name);
                        showBookingDTO.setSide(side);

                        bookingList.add(showBookingDTO);
                    }


                }
                callBack.bookingList(bookingList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

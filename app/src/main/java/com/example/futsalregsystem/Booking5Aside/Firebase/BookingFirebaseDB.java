package com.example.futsalregsystem.Booking5Aside.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.futsalregsystem.Booking5Aside.Contracts.BookingContracts;
import com.example.futsalregsystem.Booking5Aside.DTO.BookingDTO;
import com.example.futsalregsystem.Booking5Aside.View.BookingView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingFirebaseDB {
    DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;
    ArrayList<BookingDTO> scheduleList;
    String selectedDate;

    public void loadSchedule(String date, final BookingContracts.modelFirebase modelFirebase) {
        this.selectedDate = date;
        String selected_side = BookingView.side;

        scheduleList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Schedules").child(date).child(selected_side);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BookingDTO bookingDTO = new BookingDTO();
                    bookingDTO.setTime(ds.getKey());
                    bookingDTO.setStatus(String.valueOf(ds.getValue()));
                    scheduleList.add(bookingDTO);
                }
                modelFirebase.schdeuleList(scheduleList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //For changing status in firebase
    public int changeStatus(String date, String time, String side) {

        Log.e("date", date);
        Log.e("time", time);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Schedules").child(date).child(side).child(time);
        databaseReference.setValue("On Hold");
        return 1;


    }

    public void cancelBooking(String date, String time, String side) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Schedules").child(date).child(side).child(time);
        databaseReference.setValue("Book");
    }


    public void onHold(String s, String date, String time, String uname, String pn, String side, String userEmail) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("On Hold").child("Ground").child(s).child(date).child(time);
        DatabaseReference unameRef = databaseReference.child("Usernmae");
        unameRef.setValue(uname);

        DatabaseReference phoneRef = databaseReference.child("Phone Number");
        phoneRef.setValue(pn);
        DatabaseReference emailRef = databaseReference.child("Email");
        emailRef.setValue(userEmail);
    }
}

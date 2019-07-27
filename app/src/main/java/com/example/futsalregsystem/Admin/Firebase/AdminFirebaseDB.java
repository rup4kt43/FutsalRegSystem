package com.example.futsalregsystem.Admin.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.futsalregsystem.Admin.Contracts.AdminContracts;
import com.example.futsalregsystem.Admin.DTO.AdminDTO;
import com.example.futsalregsystem.Admin.View.AdminView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminFirebaseDB {
    public void retriveOnHoldBooking(String sides, String currentDate, final AdminContracts.modelFirebaseCallback callback) {
        final ArrayList<AdminDTO> detailList = new ArrayList<>();


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("On Hold").child("Ground").child(sides);


/*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailList.clear();
                for (DataSnapshot time : dataSnapshot.getChildren()) {



                    String key = time.getKey();             //10:00-11:00
                    AdminDTO adminDTO = new AdminDTO();
                    adminDTO.setTime(key);
                    Log.e("key", key);
                    for (DataSnapshot details : time.getChildren()) {

                        if (details.getKey().matches("Usernmae")) {
                            adminDTO.setName(details.getValue().toString());    // xbfhy
                        } else if (details.getKey().matches("Phone Number")) {
                            adminDTO.setNumber(details.getValue().toString());  //6655
                        }


                    }
                    detailList.add(adminDTO);
                    //TODO here
                }
                callback.infoDetailse(detailList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailList.clear();

                if (dataSnapshot.getChildrenCount() == 0) {
                    return;
                }

                for (DataSnapshot date : dataSnapshot.getChildren()) {
                    String loop_date = date.getKey();

                    for (DataSnapshot time : date.getChildren()) {

                        String key = time.getKey();             //10:00-11:00
                        AdminDTO adminDTO = new AdminDTO();
                        adminDTO.setTime(key);
                        adminDTO.setDate(loop_date);
                        Log.e("key", key);
                        for (DataSnapshot details : time.getChildren()) {

                            if (details.getKey().matches("Usernmae")) {
                                adminDTO.setName(details.getValue().toString());    // xbfhy
                            } else if (details.getKey().matches("Phone Number")) {
                                adminDTO.setNumber(details.getValue().toString());  //6655
                            }


                        }
                        detailList.add(adminDTO);
                        //TODO here
                    }
                    callback.infoDetailse(detailList);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void confirmBooking(String side, String current_date, int pos, String time, String username, String phone) {
        //removing the value from hold
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("On Hold")
                .child("Ground")
                .child(side)
                .child(current_date)
                .child(time);
        ref.removeValue();

        //changing the status from the schedule
        DatabaseReference status = FirebaseDatabase.getInstance().getReference().child("Schedules").child(current_date).child(side).child(time);
        status.setValue("Booked");
        AdminView.loadOnHoldBooking();

        //saving the booking in the booke node
        DatabaseReference booking = FirebaseDatabase.getInstance().getReference().child("Booking")
                .child(current_date)
                .child(time);
        DatabaseReference uname = booking.child("Username");


        uname.setValue(username);
        DatabaseReference uphone = booking.child("Phone");
        uphone.setValue(phone);
        DatabaseReference selected_side = booking.child("Side");
        selected_side.setValue(side);

    }
}

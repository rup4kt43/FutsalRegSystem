package com.example.futsalregsystem.BecomeRegularForm.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.BecomeRegularForm.Contracts.RegularContracts;
import com.example.futsalregsystem.BecomeRegularForm.View.RegularView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegularFirebaseDB {
    public void loadSpinnterTimeTable(String selected_side, final RegularContracts.modelFirebaseCallback callback) {
        final ArrayList<String> time = new ArrayList<>();
        String date = RegularView.total_date;

        time.add("Select Time");
        DatabaseReference scheduleRef = FirebaseDatabase.getInstance().getReference().child("Schedules")
                .child(date).child(selected_side);

        scheduleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    time.add(key);

                }
                callback.timeList(time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void saveRegularDetails(String usename, String userEmail, String userPhone, String ground, String time) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Members").child(ground).child(time);
        databaseReference.child("Name").setValue(usename);
        databaseReference.child("Email").setValue(userEmail);
        databaseReference.child("Contact").setValue(userPhone);

    }
}

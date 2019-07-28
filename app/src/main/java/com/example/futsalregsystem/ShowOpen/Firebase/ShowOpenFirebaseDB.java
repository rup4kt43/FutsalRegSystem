package com.example.futsalregsystem.ShowOpen.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.ShowOpen.Contracts.ShowOpenContracts;
import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOpenFirebaseDB {
    public void loadOpenSchedule(final ShowOpenContracts.modelFirebaseCallBack callBack) {
        final ArrayList<ShowOpenDTO> openList = new ArrayList<>();
        DatabaseReference openSchedule = FirebaseDatabase.getInstance().getReference().child("Schedules");

        openSchedule.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                openList.clear();
                for (DataSnapshot dateSnap : dataSnapshot.getChildren()) {
                    String date = dateSnap.getKey();

                    for (DataSnapshot sideSnap : dateSnap.getChildren()) {
                        String side = sideSnap.getKey();

                        for (DataSnapshot timeSnap : sideSnap.getChildren()) {
                            ShowOpenDTO showOpenDTO = new ShowOpenDTO();
w
                            String time = String.valueOf(timeSnap.getKey());
                            String status = String.valueOf(timeSnap.getValue());
                            if (status.matches("Open")) {
                                showOpenDTO.setDate(date);
                                showOpenDTO.setSide(side);
                                showOpenDTO.setTime(time);
                                openList.add(showOpenDTO);
                            }
                        }
                    }
                }

                callBack.openList(openList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

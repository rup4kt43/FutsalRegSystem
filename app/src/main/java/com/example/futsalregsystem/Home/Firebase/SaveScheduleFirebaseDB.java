package com.example.futsalregsystem.Home.Firebase;

import android.support.annotation.NonNull;

import com.example.futsalregsystem.Home.Utilites.ScheduleList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class SaveScheduleFirebaseDB {
    DatabaseReference databaseReference;
    FirebaseDatabase mDatabase;


    public void saveSchedule() {
        databaseReference = mDatabase.getInstance().getReference().child("Schedules");
        final ArrayList<String> schedule = ScheduleList.createDefaultSchedule();
        final ArrayList<String> schedule_7s = ScheduleList.createDefaultScheduleFor7s();

        //Calendar Task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int todayDate = calendar.get(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);


        for (int i = todayDate; i <= maxDay; i++) {
            final DatabaseReference monthRef = databaseReference.child(((month + 1) + "-" + i + "-" + year)).child("5A-Side : 1200 NPR");

            monthRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getChildrenCount() == 0) {
                        for (int j = 0; j < schedule.size(); j++) {
                            monthRef.child(schedule.get(j)).setValue("Open");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            final DatabaseReference monthRef_7s = databaseReference.child(((month + 1) + "-" + i + "-" + year)).child("7A-Side : 1500 NPR");

            monthRef_7s.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getChildrenCount() == 0) {
                        for (int j = 0; j < schedule_7s.size(); j++) {
                            monthRef_7s.child(schedule_7s.get(j)).setValue("Open");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }
}

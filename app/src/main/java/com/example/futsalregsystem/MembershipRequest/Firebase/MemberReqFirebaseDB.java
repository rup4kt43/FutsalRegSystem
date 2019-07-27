package com.example.futsalregsystem.MembershipRequest.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.futsalregsystem.MembershipRequest.Contracts.MemberReqContracts;
import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberReqFirebaseDB {
    public void loadRequest(final MemberReqContracts.modelFirebaseCallback callback) {
        final ArrayList<MemberReqDTO> detailsArray = new ArrayList<>();
        MemberReqDTO memberReqDTO;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Members");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailsArray.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String side = ds.getKey();
                   // memberReqDTO.setSide(side);


                    for (DataSnapshot times : ds.getChildren()) {
                       MemberReqDTO memberReqDTO= new MemberReqDTO();
                        String time = times.getKey();
                        memberReqDTO.setTime(time);


                        String contact = String.valueOf(times.child("Contact").getValue());
                        String email = String.valueOf(times.child("Email").getValue());
                        String name = String.valueOf(times.child("Name").getValue());

                        memberReqDTO.setName(name);
                        memberReqDTO.setContact(contact);
                        memberReqDTO.setEmail(email);
                        detailsArray.add(memberReqDTO);


                    }




                    callback.loadDetails(detailsArray);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

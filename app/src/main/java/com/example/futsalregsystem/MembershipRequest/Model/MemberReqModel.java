package com.example.futsalregsystem.MembershipRequest.Model;

import com.example.futsalregsystem.MembershipRequest.Contracts.MemberReqContracts;
import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;
import com.example.futsalregsystem.MembershipRequest.Firebase.MemberReqFirebaseDB;

import java.util.ArrayList;

public class MemberReqModel {
    private MemberReqFirebaseDB firebaseDB = new MemberReqFirebaseDB();
    public void loadRequest(final MemberReqContracts.presenterModelCallback callback) {
        firebaseDB.loadRequest(new MemberReqContracts.modelFirebaseCallback() {
            @Override
            public void loadDetails(ArrayList<MemberReqDTO> details) {
                callback.loadDetails(details);
            }
        });
    }
}

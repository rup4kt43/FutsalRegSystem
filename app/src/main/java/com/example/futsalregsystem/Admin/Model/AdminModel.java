package com.example.futsalregsystem.Admin.Model;

import com.example.futsalregsystem.Admin.Contracts.AdminContracts;
import com.example.futsalregsystem.Admin.DTO.AdminDTO;
import com.example.futsalregsystem.Admin.Firebase.AdminFirebaseDB;

import java.util.ArrayList;

public class AdminModel {

    AdminFirebaseDB adminFirebaseDB;
    public void loadOnHoldBooking(String sides, String currentDate, final AdminContracts.presenterModelCallback callback) {
    adminFirebaseDB = new AdminFirebaseDB();
        adminFirebaseDB.retriveOnHoldBooking(sides, currentDate, new AdminContracts.modelFirebaseCallback() {
            @Override
            public void infoDetailse(ArrayList<AdminDTO> infoList) {
                callback.infoDetails(infoList);
            }
        });
    }

    public void onConfirmHoldingBooking(String side,String current_date,int pos, String time, String username, String phone) {
        adminFirebaseDB = new AdminFirebaseDB();
        adminFirebaseDB.confirmBooking(side,current_date,pos,time,username,phone);
    }
}

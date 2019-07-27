package com.example.futsalregsystem.BecomeRegularForm.Model;

import com.example.futsalregsystem.BecomeRegularForm.Contracts.RegularContracts;
import com.example.futsalregsystem.BecomeRegularForm.Firebase.RegularFirebaseDB;

import java.util.ArrayList;

public class RegularModel {
    RegularFirebaseDB regularFirebaseDB = new RegularFirebaseDB();
    public void loadSpinnerItem(String selected_side, final RegularContracts.presenterModelCallback callback) {


        regularFirebaseDB.loadSpinnterTimeTable(selected_side, new RegularContracts.modelFirebaseCallback() {
            @Override
            public void timeList(ArrayList<String> list) {
                callback.timeList(list);
            }
        });
    }

    public void saveRegularDetails(String usename, String userEmail, String userPhone, String ground, String time) {
        regularFirebaseDB.saveRegularDetails(usename,userEmail,userPhone,ground,time);
    }
}

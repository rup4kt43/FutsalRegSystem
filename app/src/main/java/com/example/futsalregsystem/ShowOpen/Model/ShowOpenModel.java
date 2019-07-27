package com.example.futsalregsystem.ShowOpen.Model;

import com.example.futsalregsystem.ShowOpen.Contracts.ShowOpenContracts;
import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;
import com.example.futsalregsystem.ShowOpen.Firebase.ShowOpenFirebaseDB;

import java.util.ArrayList;

public class ShowOpenModel {
    public void loadOpenSchedule(final ShowOpenContracts.presenterModelCallback callback) {
        ShowOpenFirebaseDB firebaseDB = new ShowOpenFirebaseDB();
        firebaseDB.loadOpenSchedule(new ShowOpenContracts.modelFirebaseCallBack() {
            @Override
            public void openList(ArrayList<ShowOpenDTO> openList) {
                callback.openList(openList);
            }
        });
    }
}

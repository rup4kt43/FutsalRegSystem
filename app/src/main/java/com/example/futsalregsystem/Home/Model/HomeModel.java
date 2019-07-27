package com.example.futsalregsystem.Home.Model;

import com.example.futsalregsystem.Home.Firebase.SaveScheduleFirebaseDB;

public class HomeModel {
    public void saveSchedule() {
        SaveScheduleFirebaseDB saveScheduleFirebaseDB = new SaveScheduleFirebaseDB();
        saveScheduleFirebaseDB.saveSchedule();

    }
}

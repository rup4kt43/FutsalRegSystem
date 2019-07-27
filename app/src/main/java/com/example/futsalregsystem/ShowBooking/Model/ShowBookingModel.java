package com.example.futsalregsystem.ShowBooking.Model;

import com.example.futsalregsystem.ShowBooking.Contracts.ShowBookingContracts;
import com.example.futsalregsystem.ShowBooking.DTO.ShowBookingDTO;
import com.example.futsalregsystem.ShowBooking.Firebase.ShowBookingFirebaseDB;

import java.util.ArrayList;

public class ShowBookingModel {

    ShowBookingFirebaseDB showBookingFirebaseDB = new ShowBookingFirebaseDB();

    public void loadBookingList(final ShowBookingContracts.presenterModelCallback callback) {
        showBookingFirebaseDB.loadBookingList(new ShowBookingContracts.modelFirebaseCallBack() {
            @Override
            public void bookingList(ArrayList<ShowBookingDTO> bookingList) {
                callback.bookingList(bookingList);
            }
        });

    }
}
